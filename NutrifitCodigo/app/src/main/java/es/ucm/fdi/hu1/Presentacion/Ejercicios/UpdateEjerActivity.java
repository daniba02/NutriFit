package es.ucm.fdi.hu1.Presentacion.Ejercicios;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import es.ucm.fdi.hu1.DataAccess.DataBaseHelper;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioService;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioServiceInt;
import es.ucm.fdi.hu1.Negocio.Ejercicio.TipoEjercicio;
import es.ucm.fdi.hu1.Negocio.GrupoMuscular;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;
import es.ucm.fdi.hu1.Presentacion.MainActivity;
import es.ucm.fdi.hu1.R;

public class UpdateEjerActivity extends AppCompatActivity {

    private EditText ejerDetails;
    private EditText ejerTitle;

    private Spinner grupoMuscularSpinner;

    private Spinner tipoEjercicioSpinner;

    public static UpdateEjerActivity newInstance(){
        return new UpdateEjerActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ejer);

        EjercicioInfo ejer = DataBaseHelper.getInstance(null).getEjer(getIntent().getExtras().getLong("idejer"));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ejerDetails = findViewById(R.id.ActualizarEjerDetails);
        ejerTitle = findViewById(R.id.ActualizarEjerTitle);
        ejerDetails.setMovementMethod(new ScrollingMovementMethod());


        ejerTitle.setText(ejer.getTitle());
        ejerDetails.setText(ejer.getContent());

        //Spinners
        grupoMuscularSpinner = findViewById(R.id.spinnerActualizarEjGrupoMusc);

        GrupoMuscular[] grupos = GrupoMuscular.values();

        ArrayList<String> nombresGrupos = new ArrayList<>();
        int pos = 0;
        int i = 0;
        for (GrupoMuscular grupo : grupos) {
            nombresGrupos.add(grupo.name());
            if(ejer.getGrupoMuscular() == grupo)
                pos = i;
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresGrupos);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        grupoMuscularSpinner.setAdapter(adapter);
        grupoMuscularSpinner.setSelection(pos);

        tipoEjercicioSpinner = findViewById(R.id.spinnerActualizarEjTipoEj);

        TipoEjercicio[] tipos = TipoEjercicio.values();

        ArrayList<String> nombresTipos = new ArrayList<>();
        i = 0;
        for (TipoEjercicio tipo : tipos) {
            nombresTipos.add(tipo.name());
            if(ejer.getTipo() == tipo)
                pos = i;
            i++;
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresTipos);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipoEjercicioSpinner.setAdapter(adapter);
        tipoEjercicioSpinner.setSelection(pos);
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
            if(ejerTitle.getText().toString().equals("")){
                Toast.makeText(this, "Error: Introduzca un título", Toast.LENGTH_SHORT).show();
            }
            else {
                //Creamos el ejercicio
                EjercicioServiceInt ejerServ = ServiceFactory.getInstance().generateEjercicioService();

                String aux = (String) tipoEjercicioSpinner.getSelectedItem();
                TipoEjercicio type = TipoEjercicio.valueOf(aux);

                aux = (String) grupoMuscularSpinner.getSelectedItem();
                GrupoMuscular group = GrupoMuscular.valueOf(aux);

                int result = ejerServ.modificarEjercicio(new EjercicioInfo(getIntent().getExtras().getLong("idejer"),ejerTitle.getText().toString(), ejerDetails.getText().toString(), type, group));
                //Comprobamos si ha creado
                if(result >= 0){
                    Toast.makeText(this, "Ejercicio actualizado", Toast.LENGTH_SHORT).show();
                    goToMain();
                }
                else{
                    switch (result){
                        case -1:
                            Toast.makeText(this, "Error: Introduzca un título", Toast.LENGTH_SHORT).show();
                            break;
                        case -2:
                            Toast.makeText(this, "Error: Titulo repetido", Toast.LENGTH_SHORT).show();
                            break;
                        case -3:
                            Toast.makeText(this, "Error: DB problem", Toast.LENGTH_SHORT).show();
                            break;
                    }
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