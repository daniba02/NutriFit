package es.ucm.fdi.hu1.Presentacion.Rutinas;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;
import es.ucm.fdi.hu1.R;

public class AddEjToRutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private EjsRutinaAdapter adapter;
    private List<EjercicioInfo> ejers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ej_to_rut);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        long id = getIntent().getExtras().getLong("idrut");


        ejers = ServiceFactory.getInstance().generateRutinaService().getEjsdeNoRut(id);
        recyclerView = findViewById(R.id.listOfEjers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EjsRutinaAdapter(this, ejers, id);
        recyclerView.setAdapter(adapter);



    }
    
}