package services;

import java.util.ArrayList;
import java.util.List;

public class ReporteService {

    // Retorna una lista de usuarios (por ejemplo, emails o nombres) para autocompletar
    public List<String> obtenerUsuarios() {
        List<String> usuarios = new ArrayList<>();
        // Ejemplo: consulta a la base de datos para obtener usuarios
        usuarios.add("user1@example.com");
        usuarios.add("user2@example.com");
        return usuarios;
    }
    
    // Retorna una lista de libros (por ejemplo, títulos) para autocompletar
    public List<String> obtenerLibros() {
        List<String> libros = new ArrayList<>();
        // Ejemplo: consulta a la base de datos para obtener libros
        libros.add("Libro Uno");
        libros.add("Libro Dos");
        return libros;
    }
    
    /**
     * Simula la búsqueda de reportes en función del intervalo de fechas, usuario, libro y estado.
     * Retorna una lista de arreglos de Object[] que representan filas para la tabla.
     */
    public List<Object[]> buscarReportes(String fechaPrestamo, String fechaDevolucion, String usuario, String libro, String estado) {
        List<Object[]> datos = new ArrayList<>();
        // Aquí deberías implementar la consulta real en la BD.
        // Se agregan datos de ejemplo:
        datos.add(new Object[]{1, "2023-01-05", "user1@example.com", "Libro Uno", "Disponible", "Detalle A"});
        datos.add(new Object[]{2, "2023-02-10", "user2@example.com", "Libro Dos", "No Disponible", "Detalle B"});
        return datos;
    }
    
    /**
     * Simula la generación de reportes predefinidos según la opción seleccionada en el combo.
     */
    public List<Object[]> generarReporteExportar(String opcion) {
        List<Object[]> datos = new ArrayList<>();
        if (opcion.equals("Libros más prestados en los últimos 6 meses.")) {
            datos.add(new Object[]{1, "Libro Uno", 15});
            datos.add(new Object[]{2, "Libro Dos", 10});
        } else if (opcion.equals("Usuarios con más préstamos realizados en los últimos 3 meses.")) {
            datos.add(new Object[]{1, "user1@example.com", 5});
            datos.add(new Object[]{2, "user2@example.com", 3});
        } else if (opcion.equals("Porcentaje de libros por género.")) {
            datos.add(new Object[]{"Ficción", "40%"});
            datos.add(new Object[]{"No Ficción", "30%"});
            datos.add(new Object[]{"Infantil", "30%"});
        } else if (opcion.equals("Listar todos los libros que no se han prestado en el último año.")) {
            datos.add(new Object[]{1, "Libro Tres", "Disponible", "Detalle C"});
        } else if (opcion.equals("Mostrar usuarios con reservas pendientes o vencidas.")) {
            datos.add(new Object[]{1, "user3@example.com", "Pendiente"});
            datos.add(new Object[]{2, "user4@example.com", "Vencida"});
        }
        return datos;
    }
}
