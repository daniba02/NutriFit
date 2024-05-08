package es.ucm.fdi.hu1.Negocio.Rutinas;

import java.util.List;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaInfo;

public interface RutinaServiceInt {

    public RutinaInfo crearRutina(RutinaInfo rutina);

    public boolean VincularRutina(Rutina_ejercicioInfo transfer);

    public List<EjercicioInfo> getEjsdeRut(long idRut);

    public List<EjercicioInfo> getEjsdeNoRut(long idRut);

    public TOARutEjs getRutinaInfo(long idRut);

}
