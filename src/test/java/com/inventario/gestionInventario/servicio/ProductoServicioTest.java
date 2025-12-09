package com.inventario.gestionInventario.servicio;

// --- INICIO DE IMPORTS MANUALES ---
import com.inventario.gestionInventario.modelo.Producto;
import com.inventario.gestionInventario.repositorio.ProductoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

// IMPORTS ESTÁTICOS: Para usar métodos como when() y assertEquals() directamente.
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
// --- FIN DE IMPORTS MANUALES ---

@ExtendWith(MockitoExtension.class) // Habilita Mockito para JUnit 5
public class ProductoServicioTest {

    @Mock // 1. Simula el Repositorio (no toca la DB real)
    private ProductoRepositorio productoRepositorio;

    @InjectMocks // 2. Inyecta el repositorio simulado en el Servicio real
    private ProductoServicio productoServicio;

    @Test
    void debeObtenerTodosLosProductos() {
        // GIVEN (Definir el escenario)
        Producto p1 = new Producto(1L, "P1", "D1", 10.0, 5, "C1");
        Producto p2 = new Producto(2L, "P2", "D2", 20.0, 10, "C2");
        List<Producto> listaEsperada = Arrays.asList(p1, p2);

        // WHEN (Definir el comportamiento simulado)
        // Cuando alguien llama a findAll(), Mockito debe devolver listaEsperada
        when(productoRepositorio.findAll()).thenReturn(listaEsperada);

        // THEN (Ejecutar y verificar)
        List<Producto> resultado = productoServicio.obtenerTodos();

        // Afirma que el tamaño de la lista obtenida es igual al esperado
        assertEquals(2, resultado.size());
        // Afirma que el resultado es exactamente la lista que simulamos
        assertEquals("P1", resultado.get(0).getNombre());
    }

    // Aquí se añadirían más tests para guardar, actualizar, etc.
}