package com.example.acervobibliotecario.model;

import java.util.Date;

public class RetirarAcervo {
    private String UID;
    private String acervo;
    private Long data;

    public RetirarAcervo(){

    }

    public RetirarAcervo(String UID, String acervo, long data) {
        this.UID = UID;
        this.acervo = acervo;
        this.data = data;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getAcervo() {
        return acervo;
    }

    public void setAcervo(String acervo) {
        this.acervo = acervo;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RetirarAcervo{" +
                "UID='" + UID + '\'' +
                ", acervo=" + acervo +
                ", data=" + data +
                '}';
    }
}
