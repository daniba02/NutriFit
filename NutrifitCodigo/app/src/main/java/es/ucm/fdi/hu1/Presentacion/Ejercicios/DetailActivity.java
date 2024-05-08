package es.ucm.fdi.hu1.Presentacion.Ejercicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import es.ucm.fdi.hu1.DataAccess.DataBaseHelper;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.R;

public class DetailActivity extends AppCompatActivity {

    private TextView showTitle,showDetails, tipo, grupoMusc;
    private Toolbar toolbar;
    private Long id;
    private DataBaseHelper db;
    private EjercicioInfo ejer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showDetails = findViewById(R.id.showDetails);
        showTitle = findViewById(R.id.showTitle);

        db = new DataBaseHelper(this);
        Intent intent = getIntent();
        id = intent.getLongExtra("ID",0);
        ejer = db.getEjer(id);

        showTitle.setText(ejer.getTitle());
        showDetails.setText(ejer.getContent());
        showDetails.setMovementMethod(new ScrollingMovementMethod());

        grupoMusc = findViewById(R.id.grupoMuscularEjDetails);
        grupoMusc.setText(ejer.getGrupoMuscular().toString());

        tipo = findViewById(R.id.tipoEjDetails);
        tipo.setText(ejer.getTipo().toString());
    }
}