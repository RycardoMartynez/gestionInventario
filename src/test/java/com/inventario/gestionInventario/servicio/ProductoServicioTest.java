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
import java.util.Optional;

// IMPORTS ESTÁTICOS: Para usar métodos como when() y assertEquals() directamente.
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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
    @Test
    void debeGuardarUnProducto() {
        // GIVEN
        Producto productoNuevo = new Producto(null, "Laptop X1", "Gamer", 1500.0, 10, "Tech");
        // Simulamos que el repositorio devuelve el mismo producto pero con el ID generado.
        Producto productoGuardado = new Producto(3L, "Laptop X1", "Gamer", 1500.0, 10, "Tech");

        // WHEN
        when(productoRepositorio.save(productoNuevo)).thenReturn(productoGuardado);

        // THEN
        Producto resultado = productoServicio.guardarProducto(productoNuevo);

        // Verificamos que el resultado no sea nulo y que tenga un ID asignado.
        assertEquals(3L, resultado.getId());
        assertEquals("Laptop X1", resultado.getNombre());

        // Verificamos que el método save() del repositorio fue llamado exactamente una vez.
        // Esto es crucial: asegura que la interacción con la DB se simuló.
        verify(productoRepositorio, times(1)).save(productoNuevo);

    }
    @Test
    void debeObtenerProductoPorId() {
        // GIVEN
        Long idBuscado = 1L;
        Producto productoEsperado = new Producto(idBuscado, "Monitor", "4K", 500.0, 2, "Display");

        // WHEN
        // Usamos Optional.of() para simular que el producto SÍ fue encontrado.
        when(productoRepositorio.findById(idBuscado)).thenReturn(Optional.of(productoEsperado));

        // THEN
        Producto resultado = productoServicio.obtenerProductoPorId(idBuscado);

        // Verificamos que el producto devuelto es el que simulamos.
        assertEquals(idBuscado, resultado.getId());
        assertEquals("Monitor", resultado.getNombre());
    }

// Opcional: También deberías testear que lance RecursoNoEncontradoException
// cuando el ID no existe (Optional.empty()).

// Archivo: ProductoServicioTest.java (PRUEBA CORREGIDA)

// Asegúrate de que este import exista:

// ...

    @Test
    void debeEliminarProductoPorId() {
        // GIVEN
        Long idAEliminar = 1L;
        Producto productoExistente = new Producto(idAEliminar, "Temporal", "Demo", 1.0, 1, "A");

        // WHEN (Simulamos la verificación de existencia)
        // El servicio llama a findById, simulamos que encuentra el producto.
        when(productoRepositorio.findById(idAEliminar)).thenReturn(Optional.of(productoExistente));

        // Ejecutamos el método que ahora debería llamar a deleteById
        productoServicio.eliminarProducto(idAEliminar);

        // THEN (Verificamos ambas interacciones)
        // Verificamos que se llamó a findById (para la verificación de existencia)
        verify(productoRepositorio, times(1)).findById(idAEliminar);

        // Verificamos que se llamó a deleteById (para la eliminación)
        verify(productoRepositorio, times(1)).deleteById(idAEliminar);
    }
    // Aquí se añadirían más tests para guardar, actualizar, etc.
}