package dao;

import models.HistorialPrestamo;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialPrestamoDAO {
    public void registrarPrestamo(HistorialPrestamo prestamo) {
        String sql = "INSERT INTO historial_prestamos (id_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prestamo.getIdUsuario());
            stmt.setInt(2, prestamo.getIdLibro());
            stmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            stmt.setDate(4, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HistorialPrestamo> obtenerHistorial() {
        List<HistorialPrestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_prestamos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new HistorialPrestamo(
                    rs.getInt("id_historial"),
                    rs.getInt("id_usuario"),
                    rs.getInt("id_libro"),
                    rs.getDate("fecha_prestamo"),
                    rs.getDate("fecha_devolucion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
