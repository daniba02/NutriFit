package es.ucm.fdi.hu1.Presentacion.Rutinas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaServiceInt;
import es.ucm.fdi.hu1.Negocio.Rutinas.TOARutEjs;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;
import es.ucm.fdi.hu1.R;

public class DetailsActivityRutina extends AppCompatActivity {

    private TextView showTitlerut,showDetailsrut, grupoMusc;
    private Toolbar toolbar;
    private Long id;
    private RutinaServiceInt rut_service;
    private TOARutEjs toa;
    private ShowInfoRutAdapter adapter;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_rutina);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showDetailsrut = findViewById(R.id.showDetails);
        showTitlerut = findViewById(R.id.showTitle);

        Intent intent = getIntent();
        id = intent.getLongExtra("ID",0);
        rut_service = ServiceFactory.getInstance().generateRutinaService();
        toa = rut_service.getRutinaInfo(id);

        recyclerView = findViewById(R.id.listOfEjersRutina);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShowInfoRutAdapter(this, toa.getEjes());
        recyclerView.setAdapter(adapter);


        showTitlerut.setText(toa.getRut().getTitle());
        showDetailsrut.setText(toa.getRut().getContent());
        showDetailsrut.setMovementMethod(new ScrollingMovementMethod());

        grupoMusc = findViewById(R.id.grupoMuscularRutDetails);
        grupoMusc.setText(toa.getRut().getGrupoMuscular().toString());

        FloatingActionButton addejerbutton = findViewById(R.id.addEjersButton);
        addejerbutton.setOnClickListener((v) -> {

            Intent i = new Intent(this, AddEjToRutActivity.class);
            i.putExtra("idrut", id);
            startActivity(i);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fitness_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.fitnessButton){
            goToMarcarEjercicio();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMarcarEjercicio() {
        Intent i = new Intent(this, MarcarEjercicioRutinas.class);
        i.putExtra("idrut", id);
        startActivity(i);
    }
}