package es.ucm.fdi.hu1.Negocio.Ejercicio;

import es.ucm.fdi.hu1.Negocio.GrupoMuscular;

public interface EjercicioServiceInt {

    public EjercicioInfo crearEjercicio(EjercicioInfo ejer);

    public boolean eliminarEjercicio(long id);

    public int modificarEjercicio(EjercicioInfo ejer);

}
