package es.mz.tests;

/* LIBRERIAS */

import model.DiasLluvia;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DiasLluviaTest {
    static DiasLluvia diasLluvia;

    @BeforeAll
    @DisplayName("Crear clase DiasLluvia antes de los test.")
    public static void setUpBeforeClass() throws Exception {
        diasLluvia = new DiasLluvia();
    }

    @BeforeEach
    public void setUp() throws Exception {
        diasLluvia.registroDia(12, 11, true);
        diasLluvia.registroDia(30, 6, false);
        diasLluvia.registroDia(19, 7, false);
        diasLluvia.registroDia(24, 1, false);
        diasLluvia.registroDia(26, 4, true);
        diasLluvia.registroDia(19, 3, true);
        diasLluvia.registroDia(16, 11, false);
    }

    @Test
    @DisplayName("Método que prueba el registro de días con un día lluvioso. Debe añadirse.")
    public void registroDiaTestLluvioso() {
        assertTrue(diasLluvia.registroDia(12, 12, true));
    }

    @Test
    @DisplayName("Método que prueba el registro de días con un día no lluvioso. Debe añadirse.")
    public void registroDiaTestNoLluvioso() {
        assertTrue(diasLluvia.registroDia(12, 12, false));
    }

    @Test
    @DisplayName("Método que prueba el registro de días, la fecha no es válida. No debe añadirse.")
    public void registroDiaTestNoRegistrado() {
        assertFalse(diasLluvia.registroDia(32, 56, true));
    }

    @Test
    @DisplayName("Método que prueba la consulta de si un día fue lluvioso o no. Debe ser lluvioso.")
    public void consultarDiaLluvioso() {
        assertTrue(diasLluvia.consultarDia(12, 12));
    }

    @Test
    @DisplayName("Método que prueba la consulta de si un día fue lluvioso o no. No debe ser lluvioso.")
    public void consultarDiaNoLluvioso() {
        assertFalse(diasLluvia.consultarDia(30, 6));
    }

    @AfterEach
    public void tearDown() throws Exception {
        // TODO
    }

    @AfterAll
    public static void tearDownAfterClas() throws Exception {
        // TODO
    }
}
