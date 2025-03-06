package dao;

import models.Libro;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    public void actualizarLibro(Libro libro) {
    String sql = "UPDATE libros SET titulo = ?, autor = ?, id_categoria = ?, isbn = ?, cantidad_disponible = ?, fecha_publicacion = ?, disponible = ? WHERE id_libro = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, libro.getTitulo());
        stmt.setString(2, libro.getAutor());
        stmt.setInt(3, libro.getIdCategoria());
        stmt.setString(4, libro.getIsbn());  // asumiendo que has agregado getIsbn()
        stmt.setInt(5, libro.getCantidadDisponible()); // asumiendo que has agregado getCantidadDisponible()
        stmt.setString(6, libro.getFechaPublicacion());  // asumiendo que getFechaPublicacion() devuelve String o se formatea correctamente
        stmt.setString(7, libro.getEstado());
        stmt.setInt(8, libro.getIdLibro());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void eliminarLibro(int idLibro) {
    String sql = "DELETE FROM libros WHERE id_libro = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idLibro);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void agregarLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo, autor, id_categoria, disponible) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getIdCategoria());
            stmt.setString(4, libro.getEstado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Libro> obtenerLibros() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Libro(
                    rs.getInt("id_libro"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getInt("id_categoria"),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
