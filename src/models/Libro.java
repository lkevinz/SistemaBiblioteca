package models;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private int idCategoria;
    private boolean disponible;
    
    // Nuevos atributos
    private String isbn;
    private int cantidadDisponible;
    private String fechaPublicacion;

    // Constructor original (sin los nuevos campos)
    public Libro(int idLibro, String titulo, String autor, int idCategoria, boolean disponible) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.idCategoria = idCategoria;
        this.disponible = disponible;
    }
    
    // Constructor completo con todos los atributos
    public Libro(int idLibro, String titulo, String autor, int idCategoria, boolean disponible, 
                 String isbn, int cantidadDisponible, String fechaPublicacion) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.idCategoria = idCategoria;
        this.disponible = disponible;
        this.isbn = isbn;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaPublicacion = fechaPublicacion;
    }
    
    // Getters
    public int getIdLibro() { return idLibro; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getIdCategoria() { return idCategoria; }
    public boolean isDisponible() { return disponible; }
    public String getIsbn() { return isbn; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public String getFechaPublicacion() { return fechaPublicacion; }
    
    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setCantidadDisponible(int cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }
    public void setFechaPublicacion(String fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
}
