package es.ucm.fdi.hu1.Presentacion.Ejercicios;

import static es.ucm.fdi.hu1.R.drawable.border_text;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.hu1.DataAccess.DataBaseHelper;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Ejercicio.TipoEjercicio;
import es.ucm.fdi.hu1.Negocio.GrupoMuscular;
import es.ucm.fdi.hu1.R;

public class EjerciciosFragment extends Fragment implements SearchView.OnQueryTextListener{


    private EjercicioAdapter adapter;
    private List<EjercicioInfo> ejs;

    public EjerciciosFragment() {
        // Required empty public constructor
    }

    public static EjerciciosFragment newInstance(String param1, String param2) {

        return new EjerciciosFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        // Configurar el RecyclerView
        DataBaseHelper db = DataBaseHelper.getInstance(getContext());
        ejs = db.getEjercicios();
        RecyclerView recyclerView = view.findViewById(R.id.listOfEjersFr);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EjercicioAdapter(getContext(), ejs);
        recyclerView.setAdapter(adapter);

        //Configuramos los filtros
        Button buttonShowChips = view.findViewById(R.id.filterEjButton);
        ChipGroup chipGroup = view.findViewById(R.id.EjMuscGroupChipGroup);
        ChipGroup chipGroup1 = view.findViewById(R.id.EjTipoEjChipGroup);
        LinearLayout filtersLayout = view.findViewById(R.id.layoutFiltrosEjs);

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

        // Crear y agregar chips dinámicamente
        for (GrupoMuscular g : GrupoMuscular.values()) {
            Chip chip = new Chip(getContext());
            chip.setText(g.toString());
            chip.setClickable(true);
            chip.setCheckable(true); // Permitir selección
            chipGroup.addView(chip);
            chip.setOnClickListener((v) -> {
                if(chip.isChecked())
                    adapter.anadirFiltroG(g.toString());
                if(!chip.isChecked())
                    adapter.eliminarFiltroG(g.toString());

                adapter.filtrado();

            });
            chip.setBackground(ContextCompat.getDrawable(view.getContext(), border_text));
            chip.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        for (TipoEjercicio e: TipoEjercicio.values()){
            Chip chip1 = new Chip(getContext());
            chip1.setText(e.toString());
            chip1.setClickable(true);
            chip1.setCheckable(true); // Permitir selección
            chipGroup1.addView(chip1); // Usar chipGroup1 en lugar de chipGroup
            chip1.setOnClickListener((v) -> {
                if(chip1.isChecked())
                    adapter.anadirFiltroT(e.toString());
                else
                    adapter.eliminarFiltroT(e.toString());

                adapter.filtrado();

            });
            chip1.setBackground(ContextCompat.getDrawable(view.getContext(), border_text));
            chip1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        }



        // Configurar el FloatingActionButton
        FloatingActionButton addbutton = view.findViewById(R.id.addButtonEjsFr);
        addbutton.setOnClickListener((v) -> {
            Intent i = new Intent(getContext(), AddEjercicioActivity.class);
            startActivity(i);
        });

        SearchView buscador = view.findViewById(R.id.buscar_ejer);
        buscador.setOnQueryTextListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Actualizar los datos del RecyclerView
        DataBaseHelper db = DataBaseHelper.getInstance(getContext());
        ejs = db.getEjercicios();
        adapter.setEjers(ejs);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.setSearchFilter(newText);
        adapter.filtrado();
        return false;
    }
}