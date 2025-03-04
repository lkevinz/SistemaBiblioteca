package util;

public class Sesion {
    private static String usuario;
    private static String rol;

    public static void iniciarSesion(String user, String userRol) {
        usuario = user;
        rol = userRol;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getRol() {
        return rol;
    }
}
