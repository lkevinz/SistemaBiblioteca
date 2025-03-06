package models;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private int idCategoria;
    private String estado;
    
    // Nuevos atributos
    private String isbn;
    private int cantidadDisponible;
    private String fechaPublicacion;

    // Constructor original (sin los nuevos campos)
    public Libro(int idLibro, String titulo, String autor, int idCategoria, String estado) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.idCategoria = idCategoria;
        this.estado = estado;
    }
    
    // Constructor completo con todos los atributos
    public Libro(int idLibro, String titulo, String autor, int idCategoria, String estado, 
                 String isbn, int cantidadDisponible, String fechaPublicacion) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.idCategoria = idCategoria;
        this.estado = estado;
        this.isbn = isbn;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaPublicacion = fechaPublicacion;
    }
    
    // Getters
    public int getIdLibro() { return idLibro; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getIdCategoria() { return idCategoria; }
    public String getEstado() { return estado; }
    public String getIsbn() { return isbn; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public String getFechaPublicacion() { return fechaPublicacion; }
    
    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
    public void setDisponible(String estado) { this.estado = estado; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setCantidadDisponible(int cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }
    public void setFechaPublicacion(String fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
}
