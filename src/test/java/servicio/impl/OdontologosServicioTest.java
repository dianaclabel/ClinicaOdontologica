package servicio.impl;
import com.example.ClinicaOdontologica.model.OdontologoDTO;
import com.example.ClinicaOdontologica.servicios.IOdontologosSevicio;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@SpringBootConfiguration
public class OdontologosServicioTest {
    @Autowired
    IOdontologosSevicio odontologosSevicio;
    OdontologoDTO odontologo;

    @BeforeEach
    public void setUp() {
        odontologo = new OdontologoDTO();
        odontologo.setNombre("NombreTest");
        odontologo.setApellido("ApellidoTest");
        odontologo.setMatricula("1234");
    }

    @Test
    public void testRegistrarOdontologo() {
        OdontologoDTO guardado = odontologosSevicio.registrarOdontologo(odontologo);
        assertNotNull(odontologosSevicio.buscarUnOdontologo(guardado.getId()));
    }

    @Test
    public void testBuscarOdontologo() {
        OdontologoDTO guardado = odontologosSevicio.registrarOdontologo(odontologo);
        OdontologoDTO buscado = odontologosSevicio.buscarUnOdontologo(guardado.getId());
        assertEquals(guardado.getId(), buscado.getId());
        assertEquals(guardado.getApellido(), buscado.getApellido());
        assertEquals(guardado.getNombre(), buscado.getNombre());
        assertEquals(guardado.getMatricula(), buscado.getMatricula());
    }

    /*@Test
    public void testModificarOdontologo() {
        OdontologoDTO guardado = odontologosSevicio.registrarOdontologo(odontologo);
        guardado.setApellido("ModificadoTest");
        guardado.setNombre("ModificadoTest");
        guardado.setMatricula("1111");

        OdontologoDTO modificado = odontologosSevicio.modificarOdontologo(guardado);
        assertEquals(guardado.getApellido(), modificado.getApellido());
        assertEquals(guardado.getNombre(), modificado.getNombre());
        assertEquals(guardado.getMatricula(), modificado.getMatricula());
    }*/

    @Test
    public void testBuscarTodos() {
        odontologosSevicio.registrarOdontologo(odontologo);
        assertNotEquals(0, odontologosSevicio.buscarTodos().size());
    }

    @Test
    public void testEliminarOdontologo() {
        OdontologoDTO guardado = odontologosSevicio.registrarOdontologo(odontologo);
        odontologosSevicio.eliminarOdontologo(guardado.getId());
        assertNull(odontologosSevicio.buscarUnOdontologo(guardado.getId()));
    }
}
