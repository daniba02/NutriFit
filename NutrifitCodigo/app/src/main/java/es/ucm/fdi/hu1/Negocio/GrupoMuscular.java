package es.ucm.fdi.hu1.Negocio;

import androidx.annotation.NonNull;

public enum GrupoMuscular {

    NINGUNO("NINGUNO"),
    PIERNA("PIERNA"),
    PECHO("PECHO"),
    HOMBRO("HOMBRO"),
    TRICEPS("TRICEPS"),
    BICEPS("BICEPS");

    private final String interfaceName;
    GrupoMuscular(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @NonNull
    @Override
    public String toString(){
        return interfaceName;
    }
}
