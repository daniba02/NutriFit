package es.ucm.fdi.hu1.Presentacion.Rutinas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import es.ucm.fdi.hu1.DataAccess.DataBaseHelper;
import es.ucm.fdi.hu1.Negocio.GrupoMuscular;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaServiceInt;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;
import es.ucm.fdi.hu1.Presentacion.MainActivity;
import es.ucm.fdi.hu1.R;

public class AddRutinaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText rutinaDetails;
    private EditText rutinaTitle;

    private Spinner grupoMuscularSpinner;

    public static AddRutinaActivity newInstance(){
        return new AddRutinaActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rutina);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rutinaDetails = findViewById(R.id.RutinaDetails);
        rutinaTitle = findViewById(R.id.RutinaTitle);
        rutinaDetails.setMovementMethod(new ScrollingMovementMethod());

        grupoMuscularSpinner = findViewById(R.id.spinnerCrearRutinaGrupoMusc);

        GrupoMuscular[] grupos = GrupoMuscular.values();

        ArrayList<String> nombresGrupos = new ArrayList<>();

        for (GrupoMuscular grupo : grupos)
                nombresGrupos.add(grupo.name());


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresGrupos);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        grupoMuscularSpinner.setAdapter(adapter);
        grupoMuscularSpinner.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_rutina, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.saveRutina){
            if(rutinaTitle.getText().toString().equals("")){
                Toast.makeText(this, "Error: Introduzca un título", Toast.LENGTH_SHORT).show();
            }
            else {

                //Creamos el ejercicio

                RutinaServiceInt rutServ = ServiceFactory.getInstance().generateRutinaService();

                String aux = (String) grupoMuscularSpinner.getSelectedItem();
                GrupoMuscular group = GrupoMuscular.valueOf(aux);

                RutinaInfo rut = new RutinaInfo(rutinaTitle.getText().toString(), rutinaDetails.getText().toString(), group);

                int output = rutServ.crearRutina(rut).getError();
                //Comprobamos si ha creado
                if(output < 0)
                    switch (output){
                        case -1:
                            Toast.makeText(this, "Error: Introduzca un título", Toast.LENGTH_SHORT).show();
                            break;
                        case -2:
                            Toast.makeText(this, "Error: Rutina ya existente", Toast.LENGTH_SHORT).show();
                            break;
                    }

                else{
                    DataBaseHelper db = new DataBaseHelper(this);
                    db.addRutina(rut);

                    Toast.makeText(this, "Rutina guardada", Toast.LENGTH_SHORT).show();

                    RutinasFragment rutinasFragment = (RutinasFragment) getSupportFragmentManager().findFragmentByTag("RutinasFragmentTag");


                    goToMain();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }



    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }


}