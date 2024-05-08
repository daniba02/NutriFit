package es.ucm.fdi.hu1.Negocio.Ejercicio;

import androidx.annotation.NonNull;

public enum TipoEjercicio {
    NINGUNO("NINGUNO"),
    CARDIO("CARDIO"),
    FUERZA("FUERZA");

    private final String interfaceName;
    TipoEjercicio(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @NonNull
    @Override
    public String toString(){
        return interfaceName;
    }
}
