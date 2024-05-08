package es.ucm.fdi.hu1.Presentacion.Rutinas;

import static es.ucm.fdi.hu1.R.drawable.border_text;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import es.ucm.fdi.hu1.DataAccess.DataBaseHelper;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.GrupoMuscular;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaInfo;
import es.ucm.fdi.hu1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RutinasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RutinasFragment extends Fragment implements SearchView.OnQueryTextListener{

    private List<RutinaInfo> ruts;
    private RutinaAdapter adapter;


    public static RutinasFragment newInstance(String param1, String param2) {

        return new RutinasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar la vista desde el archivo XML
        View view = inflater.inflate(R.layout.fragment_rutinas, container, false);

        // Configurar el RecyclerView
        DataBaseHelper db = DataBaseHelper.getInstance(getContext());
        ruts = db.getRutinas();
        RecyclerView recyclerView = view.findViewById(R.id.listOfRuts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RutinaAdapter(getContext(), ruts);
        recyclerView.setAdapter(adapter);

        //Configuramos los filtros
        Button buttonShowChips = view.findViewById(R.id.filterRutButton);
        ChipGroup chipGroup = view.findViewById(R.id.RutsMuscGroupChipGroup);
        LinearLayout filtersLayout = view.findViewById(R.id.layoutFiltrosRuts);

        ChipGroup ejFilter = view.findViewById(R.id.RutEjFilterChipGroup);

        buttonShowChips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar la visibilidad del ChipGroup
                if (filtersLayout.getVisibility() == View.GONE) {
                    filtersLayout.setVisibility(View.VISIBLE);
                } else {
                    filtersLayout.setVisibility(View.GONE);
                }
            }
        });

        // Crear y agregar chips dinámicamente de grupo muscular
        for (GrupoMuscular g : GrupoMuscular.values()) {
            Chip chip = new Chip(getContext());
            chip.setText(g.toString());
            chip.setClickable(true);
            chip.setCheckable(true); // Permitir selección
            chipGroup.addView(chip);
            chip.setOnClickListener((v) -> {
                if(chip.isChecked())
                    adapter.anadirFiltro(g.toString());
                if(!chip.isChecked())
                    adapter.eliminarFiltro(g.toString());

                adapter.filtrado();

            });
            chip.setBackground(ContextCompat.getDrawable(view.getContext(), border_text));
            chip.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        for (EjercicioInfo g : db.getEjercicios()) {
            Chip chip = new Chip(getContext());
            chip.setText(g.getTitle());
            chip.setClickable(true);
            chip.setCheckable(true); // Permitir selección
            ejFilter.addView(chip);
            chip.setOnClickListener((v) -> {
                if (chip.isChecked()) {
                    adapter.anadirID(g.getID());
                }
                if (!chip.isChecked()) {
                    adapter.eliminarID(g.getID());
                }

                adapter.filtrado();

            });
            chip.setBackground(ContextCompat.getDrawable(view.getContext(), border_text));
            chip.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        // Configurar el FloatingActionButton
        FloatingActionButton addbutton = view.findViewById(R.id.addButton);
        addbutton.setOnClickListener((v) -> {
            Intent i = new Intent(getContext(), AddRutinaActivity.class);
            startActivity(i);

        });

        //buscador
        SearchView buscador = view.findViewById(R.id.buscar_ruts);
        buscador.setOnQueryTextListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Actualizar los datos del RecyclerView
        DataBaseHelper db = DataBaseHelper.getInstance(getContext());
        ruts = db.getRutinas();
        adapter.setRutinas(ruts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.setSearchFilter(newText
        );
        adapter.filtrado();
        return false;
    }

}