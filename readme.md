# SistemaBiblioteca - Sistema de Gestión de Biblioteca Escolar

SistemaBiblioteca es una aplicación de escritorio desarrollada en Java diseñada para administrar los procesos logísticos de una biblioteca escolar. Permite gestionar de manera eficiente el catálogo de libros, los estudiantes y la asignación de préstamos mediante una interfaz gráfica intuitiva conectada a una base de datos relacional.

## Características Principales

El sistema está dividido en cuatro módulos fundamentales integrados en una única ventana de control con acceso basado en roles:

*   **Autenticación y Roles:** Acceso diferenciado con restricciones de seguridad para perfiles de Bibliotecario (acceso total) y Estudiante (acceso de solo lectura).
*   **Gestión de Catálogo (CRUD):** Creación, edición, búsqueda y eliminación de libros. Incluye un control estricto de stock y validaciones para permitir reservas hasta agotar disponibilidad (stock 0).
*   **Módulo de Préstamos:** Asignación relacional entre libros disponibles y estudiantes. Implementado utilizando concurrencia (Threads) en segundo plano para evitar el congelamiento de la interfaz durante el registro.
*   **Reportes Estadísticos:** Generación dinámica de reportes mediante consultas SQL complejas (Top 5 libros más prestados, préstamos activos y el historial de cada estudiante).

## Arquitectura y Tecnologías

El proyecto está estructurado bajo el patrón de diseño **MVC (Modelo-Vista-Controlador)** en conjunto con el patrón **DAO (Data Access Object)**, garantizando una separación limpia entre la interfaz de usuario, la lógica de negocio y el acceso a los datos.

*   **Lenguaje:** Java
*   **Interfaz Gráfica:** Java Swing
*   **Base de Datos:** MySQL
*   **Conectividad:** JDBC (Java Database Connectivity)
*   **Entorno de Desarrollo:** Diseñado y probado en IntelliJ IDEA

## Estructura del Proyecto

```text
src/
├── app/               # Clase Main para iniciar la aplicación
├── controller/        # Clases intermediarias entre la Vista y los DAO
├── dao/               # Interfaces DAO y sus implementaciones (impl/) para consultas SQL
├── model/             # Entidades del sistema (Libro, Estudiante, Prestamo, Usuario, RolUsuario)
├── util/              # Clases utilitarias (ej. ConexionBD)
└── view/              # Interfaces gráficas (Login, GestorBiblioteca.java y .form)
```

## Como ejecutar:

1. Clonar el repositorio: `git clone [URL_DEL_REPOSITORIO]`
2. Configurar la Base de Datos: Abre tu gestor de base de datos (ej. MySQL Workbench, DBeaver o XAMPP).
3. Crea la base de datos: Nombre por defecto: `biblioteca`.
4. Ejecuta el script SQL incluido en el proyecto (el archivo `.sql` o Dump) para crear las tablas (`usuarios`, `libros`, `estudiantes`, `prestamos`) e insertar los datos de prueba.
5. Configurar las Credenciales:
   - Dirígete a la clase `ConexionBD` (dentro del paquete `util`). 
   - Actualiza las variables de conexión `(URL, USER, PASSWORD)` con las credenciales de tu servidor MySQL local.
6. Ejecutar la Aplicación:
   - Localiza la clase principal `Main.java` dentro del paquete `app`. 
   - Ejecuta el método `Main` para lanzar la interfaz gráfica de Login.

**Credenciales de Prueba:**
*   **Bibliotecario:** admin / admin
*   **Estudiante:** estudiante1 / 1234

## Conceptos aplicados

- Programación orientada a objetos.
- Operaciones CRUD.
- Persistencia de datos con conexión a base de datos.
- Manejo de concurrencia e hilos.
- Java Swing.
- Organización modular con modelo MVC.

## Autor
Javier R.
Proyecto académico desarrollado como tarea evaluada para aplicar conceptos de Java, MVC, acceso y operaciones a base de datos y programación orientada a objetos.
