package com.example.acervobibliotecario.model;

import java.util.Date;

public class Usuario {
    private String UID;
    private String nome;
    private String email;
    private Long dataNascimento;
    private String Genero;
    private String senha;

    public Usuario(String UID, String nome, String email, Long nascimento, String genero, String senha) {
        this.UID = UID;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = nascimento;
        Genero = genero;
        this.senha = senha;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNascimento() {
        return dataNascimento;
    }

    public void setNascimento(Long nascimento) {
        this.dataNascimento = nascimento;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "UID='" + UID + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", nascimento=" + dataNascimento +
                ", Genero='" + Genero + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
