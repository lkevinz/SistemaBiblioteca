package models;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String documentoIdentidad;
    private String email;
    private String telefono;
    private String tipo;
    private String contrasenaHash;

    public Usuario(int idUsuario, String nombre, String documentoIdentidad, String email, String telefono, String tipo, String contrasenaHash) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.email = email;
        this.telefono = telefono;
        this.tipo = tipo;
        this.contrasenaHash = contrasenaHash;
    }

    public int getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }
    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getTipo() { return tipo; }
    public String getContrasenaHash() { return contrasenaHash; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setContrasenaHash(String contrasenaHash) { this.contrasenaHash = contrasenaHash; }
}
