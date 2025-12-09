# 📦 Sistema de Gestión de Inventario (Backend)

Este proyecto implementa una API RESTful robusta para la gestión de productos, diseñada con una arquitectura de capas profesional (Controlador, Servicio, Repositorio). Utiliza el framework Spring Boot y PostgreSQL como base de datos.

## 🛠️ Stack Tecnológico

| Tecnología | Propósito |
| :--- | :--- |
| **Java** | Lenguaje principal (versión 21/25). |
| **Spring Boot 3** | Framework principal para el desarrollo del Backend. |
| **Spring Data JPA** | Capa de persistencia y mapeo objeto-relacional (ORM). |
| **PostgreSQL** | Base de datos relacional para el almacenamiento de datos. |
| **Lombok** | Herramienta para reducir el código repetitivo (Getters/Setters). |
| **Maven** | Herramienta de gestión de dependencias y construcción de proyectos. |

## 📐 Arquitectura

El proyecto sigue un modelo de diseño de **tres capas** para separar las responsabilidades y asegurar la escalabilidad:

1.  **Controlador (`@RestController`):** Maneja las peticiones HTTP y mapea las URLs. Se comunica únicamente con la capa de Servicio.
2.  **Servicio (`@Service`):** Contiene la **Lógica de Negocio**. Aquí se implementan las validaciones, cálculos y reglas de negocio.
3.  **Repositorio (`JpaRepository`):** Interactúa directamente con la base de datos para realizar las operaciones CRUD.

## 🚀 Funcionalidades (CRUD)

La API expone el endpoint base `/api/productos` con las siguientes operaciones:

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **POST** | `/api/productos` | Crea un nuevo producto en el inventario. |
| **GET** | `/api/productos` | Obtiene la lista completa de todos los productos. |
| **GET** | `/api/productos/{id}` | Busca y obtiene los detalles de un producto específico por su ID. |
| **PUT** | `/api/productos/{id}` | Actualiza completamente un producto existente por su ID. |
| **DELETE** | `/api/productos/{id}` | Elimina un producto del inventario por su ID. |

### Manejo de Errores

* Se implementó un manejo de excepciones global con **`@ControllerAdvice`** para interceptar errores de tipo **`RecursoNoEncontradoException`**.
* Si se intenta acceder a un recurso inexistente (ej. `GET /api/productos/999`), la API devuelve un código de estado **`404 Not Found`** con un mensaje JSON personalizado y detallado.

## ⚙️ Configuración y Ejecución Local

Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

### Prerrequisitos

* **Java JDK** (versión 21 o superior).
* **PostgreSQL** (servidor local).
* **Maven**.

### Pasos

1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://docs.github.com/es/repositories/creating-and-managing-repositories/quickstart-for-repositories](https://docs.github.com/es/repositories/creating-and-managing-repositories/quickstart-for-repositories)
    ```

2.  **Configurar la Base de Datos:**
    * Asegúrate de tener un servidor PostgreSQL activo.
    * Crea una base de datos con el nombre: `inventario_db`.

3.  **Actualizar la Conexión:**
    * Abre el archivo `src/main/resources/application.properties`.
    * Reemplaza `TU_CONTRASEÑA` por la contraseña de tu usuario de PostgreSQL (`postgres`).

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/inventario_db
    spring.datasource.username=postgres
    spring.datasource.password=TU_CONTRASEÑA
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

4.  **Ejecutar la Aplicación:**
    * Desde tu IDE (IntelliJ IDEA), ejecuta la clase principal `GestionInventarioApplication.java`.
    * Alternativamente, usando Maven en la terminal:
        ```bash
        ./mvnw spring-boot:run
        ```
    * La API estará disponible en `http://localhost:8080`.

### Pruebas con Postman

Utiliza Postman o una herramienta similar para interactuar con la API.

**Ejemplo de Petición POST (Crear Producto):**
