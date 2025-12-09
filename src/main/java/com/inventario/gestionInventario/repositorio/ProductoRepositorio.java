package com.inventario.gestionInventario.repositorio;

import com.inventario.gestionInventario.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    // Spring te da todos los métodos CRUD (Crear, Leer, Actualizar, Borrar) automáticamente.

}