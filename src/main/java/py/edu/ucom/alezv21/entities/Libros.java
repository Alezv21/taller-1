package py.edu.ucom.alezv21.entities;

public class Libros {
    
    public String titulo;
    public String autor;
    public String ISBN;
    public Boolean prestado; // S o N/  1-0 / true false

    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getISBN() {
        return ISBN;
    }
    public Boolean getPrestado() {
        return prestado;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    public void setPrestado(Boolean prestado) {
        this.prestado = prestado;
    }
    
}
