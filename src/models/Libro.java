package models;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private int idCategoria;
    private boolean disponible;

    public Libro(int idLibro, String titulo, String autor, int idCategoria, boolean disponible) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.idCategoria = idCategoria;
        this.disponible = disponible;
    }

    public int getIdLibro() { return idLibro; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getIdCategoria() { return idCategoria; }
    public boolean isDisponible() { return disponible; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
