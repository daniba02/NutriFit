package es.ucm.fdi.hu1.Negocio;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioServiceInt;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioService;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaService;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaServiceInt;

public class ServiceFactoryImp extends ServiceFactory{


    @Override
    public RutinaServiceInt generateRutinaService() {
        return new RutinaService();
    }

    @Override
    public EjercicioServiceInt generateEjercicioService() {
        return new EjercicioService();
    }
}
