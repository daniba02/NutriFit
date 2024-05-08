package es.ucm.fdi.hu1.Negocio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaService;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioService;
@RunWith(MockitoJUnitRunner.class)
public class ServiceFactoryImpTest {

    private ServiceFactoryImp factory;

    @Test
    public void testGenerateRutinaService() {
        // Crear una instancia de ServiceFactoryImp
        factory = new ServiceFactoryImp();

        // Llamar a generateRutinaService() y verificar que devuelva una instancia de RutinaService
        assertTrue(factory.generateRutinaService() instanceof RutinaService);
    }

    @Test
    public void testGenerateEjercicioService() {
        // Crear una instancia de ServiceFactoryImp
        factory = new ServiceFactoryImp();

        // Llamar a generateEjercicioService() y verificar que devuelva una instancia de EjercicioService
        assertTrue(factory.generateEjercicioService() instanceof EjercicioService);
    }
}