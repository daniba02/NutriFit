package es.ucm.fdi.hu1.Presentacion.Ejercicios;

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
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioServiceInt;
import es.ucm.fdi.hu1.Negocio.Ejercicio.TipoEjercicio;
import es.ucm.fdi.hu1.Negocio.GrupoMuscular;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;
import es.ucm.fdi.hu1.Presentacion.MainActivity;
import es.ucm.fdi.hu1.R;

public class AddEjercicioActivity extends AppCompatActivity {

    private EditText ejerDetails;
    private EditText ejerTitle;

    private Spinner grupoMuscularSpinner;

    private Spinner tipoEjercicioSpinner;

    public static AddEjercicioActivity newInstance(){
        return new AddEjercicioActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ejercicio);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ejerDetails = findViewById(R.id.EjerDetails);
        ejerTitle = findViewById(R.id.EjerTitle);
        ejerDetails.setMovementMethod(new ScrollingMovementMethod());

        grupoMuscularSpinner = findViewById(R.id.spinnerCrearEjGrupoMusc);

        GrupoMuscular[] grupos = GrupoMuscular.values();

        ArrayList<String> nombresGrupos = new ArrayList<>();

        for (GrupoMuscular grupo : grupos) {
            nombresGrupos.add(grupo.name());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresGrupos);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        grupoMuscularSpinner.setAdapter(adapter);
        grupoMuscularSpinner.setSelection(0);

        tipoEjercicioSpinner = findViewById(R.id.spinnerCrearEjTipoEj);

        TipoEjercicio[] tipos = TipoEjercicio.values();

        ArrayList<String> nombresTipos = new ArrayList<>();

        for (TipoEjercicio tipo : tipos) {
            nombresTipos.add(tipo.name());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresTipos);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipoEjercicioSpinner.setAdapter(adapter);
        tipoEjercicioSpinner.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_ejer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.saveEjer){
            if(ejerTitle.getText().toString().isEmpty()){
                Toast.makeText(this, "Error: Introduzca un título", Toast.LENGTH_SHORT).show();
            }
            else {
                //Creamos el ejercicio
                EjercicioServiceInt ejerServ = ServiceFactory.getInstance().generateEjercicioService();

                String aux = (String) tipoEjercicioSpinner.getSelectedItem();
                TipoEjercicio type = TipoEjercicio.valueOf(aux);

                aux = (String) grupoMuscularSpinner.getSelectedItem();
                GrupoMuscular group = GrupoMuscular.valueOf(aux);

                EjercicioInfo ejer = new EjercicioInfo(ejerTitle.getText().toString(), ejerDetails.getText().toString(),type, group);

                int result = ejerServ.crearEjercicio(ejer).getError();
                //Comprobamos si ha creado
                    if(result < 0) {
                    switch (result) {
                        case -1:
                            Toast.makeText(this, "Error: Introduzca un título", Toast.LENGTH_SHORT).show();
                            break;
                        case -2:
                            Toast.makeText(this, "Error: El ejercicio ya existe", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                else{
                    DataBaseHelper db = new DataBaseHelper(this);
                    db.addEjer(ejer);

                    Toast.makeText(this, "Ejercicio guardado", Toast.LENGTH_SHORT).show();
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