package es.ucm.fdi.hu1.Negocio.Rutinas;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import es.ucm.fdi.hu1.Negocio.GrupoMuscular;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;

@RunWith(MockitoJUnitRunner.class)
public class RutinaServiceTest {

    private RutinaServiceInt rutS;
    private RutinaInfo mockRutinaInfo1;
    private RutinaInfo mockRutinaInfo2;
    private RutinaInfo mockRutinaInfo3;

    @Before
    public void setUp() {
        // Inicializamos variables
        rutS = ServiceFactory.getInstance().generateRutinaService();
        mockRutinaInfo1 = mock(RutinaInfo.class);
        mockRutinaInfo2 = mock(RutinaInfo.class);
        mockRutinaInfo3 = mock(RutinaInfo.class);
    }

    @Test
    public void crearRutina() {

        // Configuramos el comportamiento de los mocks según lo que esperamos en la prueba
        when(mockRutinaInfo1.getTitle()).thenReturn("Nombre1");
        when(mockRutinaInfo1.getContent()).thenReturn("");
        when(mockRutinaInfo1.getGrupoMuscular()).thenReturn(GrupoMuscular.PIERNA);
        when(mockRutinaInfo2.getTitle()).thenReturn("Nombre2");
        when(mockRutinaInfo2.getContent()).thenReturn("desc1");
        when(mockRutinaInfo2.getGrupoMuscular()).thenReturn(GrupoMuscular.PECHO);
        when(mockRutinaInfo3.getTitle()).thenReturn("Nombre3");
        when(mockRutinaInfo3.getContent()).thenReturn("desc2");
        when(mockRutinaInfo3.getGrupoMuscular()).thenReturn(GrupoMuscular.HOMBRO);

        assertNotNull(rutS);

        RutinaInfo input = new RutinaInfo("", "", GrupoMuscular.NINGUNO);
        RutinaInfo res1 = rutS.crearRutina(input);

        input = new RutinaInfo("Nombre1", "", GrupoMuscular.PIERNA);
        RutinaInfo res2 = rutS.crearRutina(input);

        input = new RutinaInfo("Nombre2", "desc1", GrupoMuscular.PECHO);
        RutinaInfo res3 = rutS.crearRutina(input);

        input = new RutinaInfo("Nombre3", "desc2", GrupoMuscular.TRICEPS);
        RutinaInfo res4 = rutS.crearRutina(input);

        assertEquals(res1.getError(), -1);

        // Verificamos que los atributos de res2 sean iguales a los valores esperados del mockRutinaInfo1
        assertEquals(res2.getTitle(), mockRutinaInfo1.getTitle());
        assertEquals(res2.getContent(), mockRutinaInfo1.getContent());
        assertEquals(res2.getGrupoMuscular(), mockRutinaInfo1.getGrupoMuscular());

        // Verificamos que los atributos de res3 sean iguales a los valores esperados del mockRutinaInfo2
        assertEquals(res3.getTitle(), mockRutinaInfo2.getTitle());
        assertEquals(res3.getContent(), mockRutinaInfo2.getContent());
        assertEquals(res3.getGrupoMuscular(), mockRutinaInfo2.getGrupoMuscular());

        // Verificamos que los atributos de res4 sean iguales a los valores esperados del mockRutinaInfo3
        assertEquals(res4.getTitle(), mockRutinaInfo3.getTitle());
        assertEquals(res4.getContent(), mockRutinaInfo3.getContent());
        assertNotEquals(res4.getGrupoMuscular(), mockRutinaInfo3.getGrupoMuscular());

        // Verificamos que se hayan llamado los métodos necesarios en los mocks
        verify(mockRutinaInfo1).getTitle();
        verify(mockRutinaInfo1).getContent();
        verify(mockRutinaInfo1).getGrupoMuscular();
        verify(mockRutinaInfo2).getTitle();
        verify(mockRutinaInfo2).getContent();
        verify(mockRutinaInfo2).getGrupoMuscular();
        verify(mockRutinaInfo3).getTitle();
        verify(mockRutinaInfo3).getContent();
        verify(mockRutinaInfo3).getGrupoMuscular();
    }

}