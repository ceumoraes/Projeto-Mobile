package com.example.myapplication;

import java.io.Serializable;
import java.util.List;

public class Projeto implements Serializable {
    private Long id;
    private String nome;
    private String imageUrl;
    private String descricao;
    private List<String> galeria;

    public Projeto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<String> getGaleria() { return galeria; }
    public void setGaleria(List<String> galeria) { this.galeria = galeria; }
}