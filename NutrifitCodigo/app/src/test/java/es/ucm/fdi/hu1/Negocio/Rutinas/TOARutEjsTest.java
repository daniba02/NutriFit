package es.ucm.fdi.hu1.Negocio.Rutinas;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;

@RunWith(MockitoJUnitRunner.class)
public class TOARutEjsTest {

    private TOARutEjs toaRutEjs;
    private RutinaInfo rutinaInfo = mock(RutinaInfo.class);
    private EjercicioInfo ejercicioInfo1 = mock(EjercicioInfo.class);
    private EjercicioInfo ejercicioInfo2 = mock(EjercicioInfo.class);

    @Before
    public void setUp() {
        // Inicializar variable
        toaRutEjs = new TOARutEjs(rutinaInfo);
    }

    @Test
    public void testAgregarYEliminarEjercicio() {
        // Agregar ejercicio
        toaRutEjs.addEj(ejercicioInfo1);
        assertEquals(1, toaRutEjs.getEjes().size());

        // Eliminar ejercicio
        toaRutEjs.delEj(ejercicioInfo1);
        assertTrue(toaRutEjs.getEjes().isEmpty());
    }

    @Test
    public void testAgregarVariosEjercicios() {
        // Agregar múltiples ejercicios
        toaRutEjs.addEj(ejercicioInfo1);
        toaRutEjs.addEj(ejercicioInfo2);
        assertEquals(2, toaRutEjs.getEjes().size());
    }

    @Test
    public void testSetEjes() {
        // Crear lista de ejercicios
        List<EjercicioInfo> ejercicios = new ArrayList<>();
        ejercicios.add(ejercicioInfo1);
        ejercicios.add(ejercicioInfo2);

        // Establecer lista de ejercicios
        toaRutEjs.setEjes(ejercicios);
        assertEquals(2, toaRutEjs.getEjes().size());
    }
}