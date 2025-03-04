package dao;

import models.Usuario;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public String obtenerRol(String email, String password) {
    String sql = "SELECT tipo FROM usuarios WHERE email = ? AND contrasena_hash = SHA2(?, 256)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("tipo"); // Devuelve el rol
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Retorna null si el usuario no existe
    }


    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, documento_identidad, email, telefono, tipo, contrasena_hash) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getDocumentoIdentidad());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getTipo());
            stmt.setString(6, usuario.getContrasenaHash()); // Contraseña encriptada
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validarUsuario(String email, String password) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND contrasena_hash = SHA2(?, 256)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre, documento_identidad, email, telefono, tipo FROM usuarios";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("documento_identidad"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("tipo"),
                    "" // No recuperamos la contraseña por seguridad
                );
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }
}
