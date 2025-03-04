package services;

import dao.LibroDAO;
import models.Libro;
import java.util.List;

public class LibroService {
    private LibroDAO libroDAO = new LibroDAO();

    public void registrarLibro(String titulo, String autor, int idCategoria, boolean disponible) {
        if (titulo.isEmpty() || autor.isEmpty()) {
            System.out.println("El título y el autor son obligatorios.");
            return;
        }
        Libro libro = new Libro(0, titulo, autor, idCategoria, disponible);
        libroDAO.agregarLibro(libro);
        System.out.println("Libro registrado con éxito.");
    }

    public List<Libro> listarLibros() {
        return libroDAO.obtenerLibros();
    }
}
