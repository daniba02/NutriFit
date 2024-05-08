package es.ucm.fdi.hu1.Presentacion.Rutinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaServiceInt;
import es.ucm.fdi.hu1.Negocio.Rutinas.TOARutEjs;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;
import es.ucm.fdi.hu1.Presentacion.Ejercicios.EjercicioAdapter;
import es.ucm.fdi.hu1.R;

public class MarcarEjercicioRutinas extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private EjsRutinaAdapter2 adapter;
    private List<EjercicioInfo> ejers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_ejercicio_rutinas);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        long id = getIntent().getExtras().getLong("idrut");
        ejers = ServiceFactory.getInstance().generateRutinaService().getEjsdeRut(id);
        recyclerView = findViewById(R.id.EjersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EjsRutinaAdapter2(this, ejers, id);
        recyclerView.setAdapter(adapter);
    }
}