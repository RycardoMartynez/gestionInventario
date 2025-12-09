package com.inventario.gestionInventario.controlador;

import com.inventario.gestionInventario.modelo.Producto;
import com.inventario.gestionInventario.servicio.ProductoServicio; // Importación nueva
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    // Cambiamos a inyectar el SERVICIO, no el Repositorio
    @Autowired
    private ProductoServicio productoServicio;

    // METODO 1: OBTENER TODOS LOS PRODUCTOS (GET)
    @GetMapping
    public List<Producto> obtenerTodos() {
        // Llama al servicio
        return productoServicio.obtenerTodos();
    }

    // METODO 2: GUARDAR UN PRODUCTO (POST)
    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        // Llama al servicio
        return productoServicio.guardarProducto(producto);
    }
    // METODO 3: ACTUALIZAR UN PRODUCTO (PUT)
    @PutMapping("/{id}") // El {id} en la URL indica qué producto actualizar
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        return productoServicio.actualizarProducto(id, productoDetalles);
    }

    // METODO 4: BORRAR UN PRODUCTO (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoServicio.eliminarProducto(id);
        // Devuelve una respuesta vacía (204 No Content) indicando éxito
        return ResponseEntity.noContent().build();
    }
    // METODO 3: OBTENER UN PRODUCTO POR ID (GET)
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productoServicio.obtenerProductoPorId(id);
    }
}