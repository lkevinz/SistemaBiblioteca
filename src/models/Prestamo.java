package models;

import java.util.Date;

public class Prestamo {
    private int idPrestamo;
    private int idUsuario;
    private int idLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    // Constructor completo (por ejemplo, para cuando se recupera de la BD)
    public Prestamo(int idPrestamo, int idUsuario, int idLibro, Date fechaPrestamo, Date fechaDevolucion) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }
    
    // Constructor para crear un préstamo nuevo (sin id)
    public Prestamo(int idUsuario, int idLibro, Date fechaPrestamo, Date fechaDevolucion) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getIdPrestamo() { return idPrestamo; }
    public int getIdUsuario() { return idUsuario; }
    public int getIdLibro() { return idLibro; }
    public Date getFechaPrestamo() { return fechaPrestamo; }
    public Date getFechaDevolucion() { return fechaDevolucion; }

    public void setFechaDevolucion(Date fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
}
