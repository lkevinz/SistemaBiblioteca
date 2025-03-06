package dao;

import models.Prestamo;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    // Método para registrar un préstamo. Se inserta el estado "activo" al momento de guardar.
    public void registrarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (id_usuario, id_libro, fecha_prestamo, fecha_devolucion_esperada, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prestamo.getIdUsuario());
            stmt.setInt(2, prestamo.getIdLibro());
            stmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            stmt.setDate(4, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            // Se establece el estado inicial como "activo"
            stmt.setString(5, "activo");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los préstamos, incluyendo el campo estado.
    public List<Prestamo> obtenerPrestamos() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Prestamo(
                    rs.getInt("id_prestamo"),
                    rs.getInt("id_usuario"),
                    rs.getInt("id_libro"),
                    rs.getDate("fecha_prestamo"),
                    rs.getDate("fecha_devolucion_esperada"),
                    rs.getString("estado")  // Se recupera el estado ("activo" o "devuelto")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    // Método para devolver un préstamo: actualiza el estado de "activo" a "devuelto".
    public boolean devolverPrestamo(int idPrestamo) {
        String sql = "UPDATE prestamos SET estado = 'devuelto' WHERE id_prestamo = ? AND estado = 'activo'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPrestamo);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
