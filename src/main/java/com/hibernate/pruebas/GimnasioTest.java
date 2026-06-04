package com.hibernate.pruebas;

import com.hibernate.model.Cliente;
import com.hibernate.model.Entrenador;
import com.hibernate.util.Validaciones;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GimnasioTest {

    /** Verifica que los campos del cliente se asignan correctamente. */
    @Test
    void testCrearCliente() {
        Cliente c = new Cliente("Hector", "hector@gym.com", "612345678", LocalDate.now());
        assertEquals("Hector", c.getNombre());
        assertEquals("hector@gym.com", c.getEmail());
        assertEquals("612345678", c.getTelefono());
    }

    /** Verifica emails válidos e inválidos. */
    @Test
    void testValidarEmail() {
        assertTrue(Validaciones.validarEmail("hector@gmail.com"));
        assertFalse(Validaciones.validarEmail("hectorsingmail"));
        assertFalse(Validaciones.validarEmail(null));
    }

    /** Verifica que se asigna y desasigna entrenador correctamente. */
    @Test
    void testAsignarEntrenador() {
        Cliente c = new Cliente("Hector", "hector@gym.com", "612345678", LocalDate.now());
        assertNull(c.getEntrenador());

        Entrenador e = new Entrenador("Carlos", "Musculación");
        c.setEntrenador(e);
        assertEquals("Carlos", c.getEntrenador().getNombre());

        c.setEntrenador(null);
        assertNull(c.getEntrenador());
    }
}