package com.thiago_viotto.projetonewsapi.model;

public class Noticia {
    private String autor;
    private String titulo;
    private String descricao;
    private String url;
    private String imagemURL;
    private String data;

    public Noticia(String imagemURL, String autor, String titulo, String descricao, String data, String url){
        this.imagemURL = imagemURL;
        this.autor = autor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.url = url;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    public String getImagemURL() {
        return imagemURL;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
