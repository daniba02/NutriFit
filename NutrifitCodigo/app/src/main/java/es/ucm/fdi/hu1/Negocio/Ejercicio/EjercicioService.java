package es.ucm.fdi.hu1.Negocio.Ejercicio;

import es.ucm.fdi.hu1.DataAccess.DataBaseHelper;
import es.ucm.fdi.hu1.Negocio.GrupoMuscular;

public class EjercicioService implements EjercicioServiceInt{

    public EjercicioInfo crearEjercicio(EjercicioInfo input){

        String title = input.getTitle();
        String desc = input.getContent();
        TipoEjercicio tipo = input.getTipo();
        GrupoMuscular grupoMuscular = input.getGrupoMuscular();
        boolean ok = true;

        EjercicioInfo ejer = new EjercicioInfo(title, desc, tipo, grupoMuscular);

        if (!ejer.isCorrect())
            ejer.setError(-1);
        else if (existeEjercicio(title, -1))
            ejer.setError(-2);

        //if(!ok) ejer = null;

        return ejer;
    }

    private boolean existeEjercicio(String title, long id){
        boolean existe = false;
        DataBaseHelper db = DataBaseHelper.getInstance(null);
        EjercicioInfo ejer;
        //si existe un ejercicio con ese nombre
        ejer = db.getEjer(title);
        if(ejer != null && ejer.getID() != id)
            existe = true;

        return existe;
    }

    @Override
    public boolean eliminarEjercicio(long id){
        boolean ok = true;
        DataBaseHelper db = DataBaseHelper.getInstance(null);

        //si esta en alguna rutina
        if(!db.getRutsDeEjs(id).isEmpty())
            ok = false;
        //intentamos borrar en la bd
        if(ok)
            ok = db.deleteEj(id);

        return ok;
    }

    @Override
    public int modificarEjercicio(EjercicioInfo ejer) {

        if (ejer.getTitle().equalsIgnoreCase(" "))
            return -1;
        if (existeEjercicio(ejer.getTitle(), ejer.getID())) {
            return -2; //ejercicio con ese titulo existe
        }
        boolean ok = DataBaseHelper.getInstance(null).updateEj(ejer);

        if (!ok)
            return -3;

        return 1;
    }
}
