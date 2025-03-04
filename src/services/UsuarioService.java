package services;

import dao.UsuarioDAO;
import models.Usuario;
import util.PasswordUtils;
import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void registrarUsuario(String nombre, String documentoIdentidad, String email, String telefono, String tipo, String password) {
        if (nombre.isEmpty() || documentoIdentidad.isEmpty() || email.isEmpty() || telefono.isEmpty() || tipo.isEmpty() || password.isEmpty()) {
            System.out.println("Todos los campos son obligatorios.");
            return;
        }

        // Encriptar la contraseña antes de guardarla
        String passwordEncriptada = PasswordUtils.hashPassword(password);

        Usuario usuario = new Usuario(0, nombre, documentoIdentidad, email, telefono, tipo, passwordEncriptada);
        usuarioDAO.agregarUsuario(usuario);
        System.out.println("Usuario registrado con éxito.");
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }
}