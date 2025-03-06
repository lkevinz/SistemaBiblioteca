package models;

import java.util.Date;

public class Prestamo {
    private int idPrestamo;
    private int idUsuario;
    private int idLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado; // Nuevo atributo para el estado

    // Constructor completo con estado (usado al recuperar de la BD)
    public Prestamo(int idPrestamo, int idUsuario, int idLibro, Date fechaPrestamo, Date fechaDevolucion, String estado) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }
    
    // Constructor para crear un préstamo nuevo (sin id), se asume el estado inicial "activo"
    public Prestamo(int idUsuario, int idLibro, Date fechaPrestamo, Date fechaDevolucion) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = "activo"; // Estado inicial
    }

    public int getIdPrestamo() { return idPrestamo; }
    public int getIdUsuario() { return idUsuario; }
    public int getIdLibro() { return idLibro; }
    public Date getFechaPrestamo() { return fechaPrestamo; }
    public Date getFechaDevolucion() { return fechaDevolucion; }
    public String getEstado() { return estado; }

    public void setFechaDevolucion(Date fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
    public void setEstado(String estado) { this.estado = estado; }
}
