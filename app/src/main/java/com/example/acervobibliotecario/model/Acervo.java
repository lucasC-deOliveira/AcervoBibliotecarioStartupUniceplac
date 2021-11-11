package com.example.acervobibliotecario.model;

public class Acervo {
    private String UID;
    private String titulo;
    private String isbn;
    private String autor;
    private String edicao;


    public Acervo(){

    }

    public Acervo(String UID, String titulo, String isbn, String autor, String edicao) {
        this.UID = UID;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.edicao = edicao;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    @Override
    public String toString() {
        return "Acervo{" +
                "UID='" + UID + '\'' +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", autor='" + autor + '\'' +
                ", edicao='" + edicao + '\'' +
                '}';
    }
}
