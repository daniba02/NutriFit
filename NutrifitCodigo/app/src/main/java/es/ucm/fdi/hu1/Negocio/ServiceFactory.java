package es.ucm.fdi.hu1.Negocio;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioServiceInt;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaServiceInt;

public abstract class  ServiceFactory {

    private static ServiceFactory instance;

    public static ServiceFactory getInstance(){
        if(instance == null)
            instance = new ServiceFactoryImp();
        return instance;
    }

    public abstract RutinaServiceInt generateRutinaService();

    public abstract EjercicioServiceInt generateEjercicioService();

}
