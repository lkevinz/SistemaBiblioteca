package services;

import dao.CategoriaDAO;
import models.Categoria;
import java.util.List;

public class CategoriaService {
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void registrarCategoria(String nombre) {
        if (nombre.isEmpty()) {
            System.out.println("El nombre de la categoría no puede estar vacío.");
            return;
        }
        Categoria categoria = new Categoria(0, nombre);
        categoriaDAO.agregarCategoria(categoria);
        System.out.println("Categoría registrada con éxito.");
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.obtenerCategorias();
    }
}
