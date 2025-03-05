package services;

import dao.UsuarioDAO;
import models.Usuario;
import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }
    
    // Método para registrar un usuario
    public void registrarUsuario(Usuario usuario) {
        usuarioDAO.agregarUsuario(usuario);
    }
    
    // Método para actualizar un usuario existente
    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizarUsuario(usuario);
    }
    
    // Método para eliminar un usuario dado su ID
    public void eliminarUsuario(int idUsuario) {
        usuarioDAO.eliminarUsuario(idUsuario);
    }
}

