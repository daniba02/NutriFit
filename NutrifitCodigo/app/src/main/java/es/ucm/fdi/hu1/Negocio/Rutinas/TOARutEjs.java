package es.ucm.fdi.hu1.Negocio.Rutinas;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;

public class TOARutEjs {

    RutinaInfo rut;
    List<EjercicioInfo> ejes;

    public TOARutEjs(RutinaInfo rut){
        this.rut = rut;
        ejes = new ArrayList<EjercicioInfo>();
    }


    public RutinaInfo getRut() {
        return rut;
    }

    public void setRut(RutinaInfo rut) {
        this.rut = rut;
    }

    public List<EjercicioInfo> getEjes() {
        return ejes;
    }

    public void addEj(EjercicioInfo e) {
        ejes.add(e);
    }

    public void delEj(EjercicioInfo e){
        ejes.remove(e);
    }

    public void setEjes(List<EjercicioInfo> ejes) {
        this.ejes = ejes;
    }
}
