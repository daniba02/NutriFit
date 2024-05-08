package es.ucm.fdi.hu1.Negocio.Rutinas;
import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.hu1.DataAccess.DataBaseHelper;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.GrupoMuscular;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaInfo;

public class RutinaService implements RutinaServiceInt {

    private DataBaseHelper db;

    public RutinaInfo crearRutina(RutinaInfo input) {

        String title = input.getTitle();
        String desc = input.getContent();
        GrupoMuscular grupoMuscular = input.getGrupoMuscular();

        RutinaInfo rut = new RutinaInfo(title, desc, grupoMuscular);
        if(!rut.isCorrect())
            rut.setError(-1);

        // Comentar para probar los tests unitarios
        DataBaseHelper db = DataBaseHelper.getInstance(null);
        if(db.getRutina(title) != null)
            rut.setError(-2);
        //

        return rut;
    }

    @Override
    public boolean VincularRutina(Rutina_ejercicioInfo transfer) {
        boolean okey = true;
        DataBaseHelper db =DataBaseHelper.getInstance(null);
        List<Rutina_ejercicioInfo> lista = db.getEjerciciosDeRutina(transfer.getID_rutina());

        //Existen
        for (Rutina_ejercicioInfo o : lista) {//no están vinculados
            if(o.getID_ejer() == transfer.getID_ejer()) {
                return false;
            }
        }

        if(okey){

            okey = db.addEjercicioARutina(transfer);
        }

        return okey;
    }

    @Override
    public List<EjercicioInfo> getEjsdeRut(long idRut){
        DataBaseHelper db = DataBaseHelper.getInstance(null);
        List<Rutina_ejercicioInfo> rel = db.getEjerciciosDeRutina(idRut);

        List<EjercicioInfo> ejers = db.getEjercicios();

        List<EjercicioInfo> result = new ArrayList<>();

        for(EjercicioInfo i : ejers){
            boolean esta = false;
            for(Rutina_ejercicioInfo e : rel){
                if(i.getID() == e.getID_ejer()){
                    esta =true;

                }
            }
            if(esta){
                result.add(i);
            }
        }

        return result;
    }

    @Override
    public List<EjercicioInfo> getEjsdeNoRut(long idRut){
        DataBaseHelper db = DataBaseHelper.getInstance(null);
        List<Rutina_ejercicioInfo> rel = db.getEjerciciosDeRutina(idRut);

        List<EjercicioInfo> ejers = db.getEjercicios();

        List<EjercicioInfo> result = new ArrayList<>();

        for(EjercicioInfo i : ejers){
            boolean esta = false;
            for(Rutina_ejercicioInfo e : rel){
                if(i.getID() == e.getID_ejer()){
                    esta =true;

                }
            }
            if(!esta){
                result.add(i);
            }
        }

        return result;
    }


    @Override
    public TOARutEjs getRutinaInfo(long idRut){
        DataBaseHelper db = DataBaseHelper.getInstance(null);

        RutinaInfo rut = db.getRutina(idRut);

        if(rut == null)
            return null;

        List<Rutina_ejercicioInfo> rel = db.getEjerciciosDeRutina(idRut);
        List<EjercicioInfo> ejers = db.getEjercicios();
        List<EjercicioInfo> result = new ArrayList<>();

        for(EjercicioInfo i : ejers){
            boolean esta = false;
            for(Rutina_ejercicioInfo e : rel){
                if(i.getID() == e.getID_ejer()){
                    esta =true;

                }
            }
            if(esta){
                result.add(i);
            }
        }
        TOARutEjs toa = new TOARutEjs(rut);
        toa.setEjes(result);
        return toa;
    }

}