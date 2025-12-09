package com.inventario.gestionInventario.servicio;

import com.inventario.gestionInventario.excepciones.RecursoNoEncontradoException;
import com.inventario.gestionInventario.modelo.Producto;
import com.inventario.gestionInventario.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 1. Marca esta clase como un componente de servicio (el cerebro)
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio; // Inyecta el acceso a la DB

    // Método para obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepositorio.findAll();
    }

    // Método para guardar un producto (aquí pondríamos validaciones futuras)
    public Producto guardarProducto(Producto producto) {
        // Lógica de negocio futura iría aquí (ej. validar stock, calcular margen)
        return productoRepositorio.save(producto);
    }
    // Método para actualizar un producto existente
    public Producto actualizarProducto(Long id, Producto productoDetalles) {
        // 1. Buscar el producto por ID. Usamos orElseThrow para lanzar una excepción si no existe.
        Producto productoExistente = productoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con ID: " + id));

        // 2. Actualizar los campos
        productoExistente.setNombre(productoDetalles.getNombre());
        productoExistente.setDescripcion(productoDetalles.getDescripcion());
        productoExistente.setPrecio(productoDetalles.getPrecio());
        productoExistente.setStock(productoDetalles.getStock());
        productoExistente.setCategoria(productoDetalles.getCategoria());

        // 3. Guardar y devolver el producto actualizado
        return productoRepositorio.save(productoExistente);
    }
    // Archivo: ProductoServicio.java (CÓDIGO CORREGIDO)
    public void eliminarProducto(Long id) {
        // 1. Verifica si existe (y lanza error si no)
        Producto productoExistente = productoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con ID: " + id));

        // 2. Ejecuta la eliminación sobre el Repositorio (SOLUCIÓN)
        productoRepositorio.deleteById(id);
    }
    // Método para buscar un único producto por ID
    public Producto obtenerProductoPorId(Long id) {
        return productoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con ID: " + id));
    }


}