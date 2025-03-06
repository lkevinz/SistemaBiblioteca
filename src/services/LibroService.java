package services;

import dao.LibroDAO;
import models.Libro;
import java.util.List;
import java.util.stream.Collectors;

public class LibroService {
    private LibroDAO libroDAO = new LibroDAO();
    
    // Actualiza un libro existente
    public void actualizarLibro(Libro libro) {
        libroDAO.actualizarLibro(libro);
    }
    
    // Elimina un libro por su id
    public void eliminarLibro(int idLibro) {
        libroDAO.eliminarLibro(idLibro);
    }
    
    /**
     * Lista TODOS los libros (sin filtrar por disponibilidad).
     */
    public List<Libro> listarTodosLibros() {
        return libroDAO.obtenerLibros();
    }
    
    // Método para registrar un nuevo libro en la base de datos
    public void registrarLibro(Libro libro) {
        libroDAO.agregarLibro(libro);
    }
    
    // Se asume que solo se listan los libros disponibles para préstamo
    public List<Libro> listarLibrosDisponibles() {
        List<Libro> todos = libroDAO.obtenerLibros();
        return todos.stream()
            .filter(libro -> "true".equalsIgnoreCase(libro.getEstado()))
            .collect(Collectors.toList());

    }
    
    // Retorna títulos únicos para autocompletar
    public List<String> obtenerTitulosLibros() {
        return libroDAO.obtenerLibros().stream()
                       .map(Libro::getTitulo)
                       .distinct()
                       .collect(Collectors.toList());
    }
    
    // Retorna autores únicos para autocompletar
    public List<String> obtenerAutoresLibros() {
        return libroDAO.obtenerLibros().stream()
                       .map(Libro::getAutor)
                       .distinct()
                       .collect(Collectors.toList());
    }
    
    /**
     * Busca libros disponibles que coincidan con los filtros.
     * Si el título o autor están vacíos, no se filtra por ellos.
     * Si idCategoria es 0, se consideran todas las categorías.
     */
    public List<Libro> buscarLibrosDisponibles(String titulo, String autor, int idCategoria) {
        List<Libro> disponibles = listarLibrosDisponibles();
        
        if (!titulo.isEmpty()) {
            disponibles = disponibles.stream()
                .filter(lib -> lib.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
        }
        
        if (!autor.isEmpty()) {
            disponibles = disponibles.stream()
                .filter(lib -> lib.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());
        }
        
        if (idCategoria != 0) {
            disponibles = disponibles.stream()
                .filter(lib -> lib.getIdCategoria() == idCategoria)
                .collect(Collectors.toList());
        }
        
        return disponibles;
    }
    
    /**
     * Busca libros (sin filtrar por disponibilidad) que coincidan con los filtros.
     * Si el título o autor están vacíos, no se filtra por ellos.
     * Si idCategoria es 0, se consideran todas las categorías.
     */
    public List<Libro> buscarLibros(String titulo, String autor, int idCategoria) {
        List<Libro> libros = listarTodosLibros();
        
        if (!titulo.isEmpty()) {
            libros = libros.stream()
                .filter(lib -> lib.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
        }
        
        if (!autor.isEmpty()) {
            libros = libros.stream()
                .filter(lib -> lib.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());
        }
        
        if (idCategoria != 0) {
            libros = libros.stream()
                .filter(lib -> lib.getIdCategoria() == idCategoria)
                .collect(Collectors.toList());
        }
        
        return libros;
    }
    
    // Para acceder al DAO desde otros módulos (por ejemplo, para registrar en LibrosFrame)
    public LibroDAO getLibroDAO() {
        return libroDAO;
    }
}
