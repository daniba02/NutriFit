package es.ucm.fdi.hu1.Presentacion;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import es.ucm.fdi.hu1.DataAccess.DataBaseHelper;
import es.ucm.fdi.hu1.Presentacion.Ejercicios.EjerciciosFragment;
import es.ucm.fdi.hu1.Presentacion.Rutinas.RutinasFragment;
import es.ucm.fdi.hu1.R;

public class MainActivity extends AppCompatActivity {

    private static final String CURRENT_FRAGMENT_KEY = "current_fragment";
    private int currentFragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Inicializa la bd
        DataBaseHelper.getInstance(this);

        int nightModeFlags = getApplicationContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            /* si esta activo el modo oscuro lo desactiva */
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Recuperamos el estado del fragmento actual si existe
        if (savedInstanceState != null) {
            currentFragmentId = savedInstanceState.getInt(CURRENT_FRAGMENT_KEY);
        } else {
            // Por defecto, mostrar el fragmento de ejercicios
            currentFragmentId = R.id.exerciseButton;
        }

        // Mostramos el fragmento correspondiente al estado guardado
        showFragment(currentFragmentId);

        BottomNavigationView navView = findViewById(R.id.bottomNav);
        final int idExercise = R.id.exerciseButton;
        final int idRutina = R.id.routineButton;
        navView.setOnItemSelectedListener(menuItem -> {

            int id = menuItem.getItemId();

            if (id == idRutina) {
                currentFragmentId = idRutina;
            } else if (id == idExercise) {
                currentFragmentId = idExercise;
            }
            // Mostramos el fragmento correspondiente al botón seleccionado
            showFragment(currentFragmentId);
            return true;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Guardamos el estado actual del fragmento en caso de que se destruya la actividad
        outState.putInt(CURRENT_FRAGMENT_KEY, currentFragmentId);
    }

    private void showFragment(int fragmentId) {
        Fragment fragment;
        if (fragmentId == R.id.exerciseButton) {
            fragment = new EjerciciosFragment();
        } else {
            fragment = new RutinasFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


}



