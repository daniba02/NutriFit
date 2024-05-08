package es.ucm.fdi.hu1.Negocio.Ejercicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioServiceInt;
import es.ucm.fdi.hu1.Negocio.Ejercicio.TipoEjercicio;
import es.ucm.fdi.hu1.Negocio.GrupoMuscular;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;

@RunWith(MockitoJUnitRunner.class)
public class EjercicioServiceTest {
    private EjercicioServiceInt ejerS;
    private EjercicioInfo mockEjercicioInfo1;
    private EjercicioInfo mockEjercicioInfo2;

    @Before
    public void setUp() {
        // Inicializamos variables
        ejerS = ServiceFactory.getInstance().generateEjercicioService();
        mockEjercicioInfo1 = mock(EjercicioInfo.class);
        mockEjercicioInfo2 = mock(EjercicioInfo.class);
    }

    @Test
    public void crearEjercicio() {

        // Configuramos el comportamiento de los mocks según lo que esperamos en la prueba
        when(mockEjercicioInfo1.getTitle()).thenReturn("Nombre1");
        when(mockEjercicioInfo1.getContent()).thenReturn("");
        when(mockEjercicioInfo1.getTipo()).thenReturn(TipoEjercicio.FUERZA);
        when(mockEjercicioInfo1.getGrupoMuscular()).thenReturn(GrupoMuscular.PECHO);

        when(mockEjercicioInfo2.getTitle()).thenReturn("Nombre2");
        when(mockEjercicioInfo2.getContent()).thenReturn("desc1");
        when(mockEjercicioInfo2.getTipo()).thenReturn(TipoEjercicio.CARDIO);
        when(mockEjercicioInfo2.getGrupoMuscular()).thenReturn(GrupoMuscular.PIERNA);

        // Creamos los ejercicios
        assertNotNull(ejerS);

        EjercicioInfo input = new EjercicioInfo("", "", TipoEjercicio.NINGUNO, GrupoMuscular.NINGUNO);
        EjercicioInfo res1 = ejerS.crearEjercicio(input);

        input = new EjercicioInfo("Nombre1", "", TipoEjercicio.FUERZA, GrupoMuscular.PECHO);
        EjercicioInfo res2 = ejerS.crearEjercicio(input);

        input = new EjercicioInfo("Nombre2", "desc1", TipoEjercicio.FUERZA, GrupoMuscular.TRICEPS);
        EjercicioInfo res3 = ejerS.crearEjercicio(input);

        // Verificamos que el error en res1 sea el apropiado
        assertEquals(res1.getError(), -1);

        // Verificamos que los atributos de res2 sean iguales a los valores esperados del mockEjercicioInfo2
        assertEquals(res2.getTitle(), mockEjercicioInfo1.getTitle());
        assertEquals(res2.getContent(), mockEjercicioInfo1.getContent());
        assertEquals(res2.getTipo(), mockEjercicioInfo1.getTipo());
        assertEquals(res2.getGrupoMuscular(), mockEjercicioInfo1.getGrupoMuscular());

        // Verificamos que los atributos de res3 sean iguales a los valores esperados del mockEjercicioInfo3
        assertEquals(res3.getTitle(), mockEjercicioInfo2.getTitle());
        assertEquals(res3.getContent(), mockEjercicioInfo2.getContent());
        assertNotEquals(res3.getTipo(), mockEjercicioInfo2.getTipo());
        assertNotEquals(res3.getGrupoMuscular(), mockEjercicioInfo2.getGrupoMuscular());

        // Verificamos que se hayan llamado los métodos necesarios en los mocks
        verify(mockEjercicioInfo1).getTitle();
        verify(mockEjercicioInfo1).getContent();
        verify(mockEjercicioInfo1).getTipo();
        verify(mockEjercicioInfo1).getGrupoMuscular();

        verify(mockEjercicioInfo2).getTitle();
        verify(mockEjercicioInfo2).getContent();
        verify(mockEjercicioInfo2).getTipo();
        verify(mockEjercicioInfo2).getGrupoMuscular();
    }
}