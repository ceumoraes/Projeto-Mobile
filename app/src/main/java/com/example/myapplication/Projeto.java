package com.example.myapplication;
// Define o pacote em que a classe está. Ajuda a organizar o código e evitar conflitos de nomes.


import java.io.Serializable;
// Importa a interface Serializable, permitindo que objetos da classe sejam convertidos em bytes
// para passar entre Activities, salvar em arquivos ou enviar pela rede.
import java.util.List;
// Importa a interface List, usada para criar listas de
// objetos (aqui será usada para a galeria de imagens).


// Define a classe Projeto.
// "implements Serializable" permite que objetos desta classe sejam serializados.

public class Projeto implements Serializable {

    private Long id;// Identificador único do projeto. Long permite valores grandes.
    private String nome; // Nome do projeto.
    private String imageUrl; // URL da imagem principal do projeto.
    private String descricao;  // Descrição do projeto.
    private List<String> galeria;   // Lista de URLs ou nomes de imagens que compõem a galeria do projeto.

    public Projeto() {}
    // Construtor vazio. Necessário para frameworks como Firebase e para serialização.

    public Long getId() { return id; }
    // Getter para o id. Permite ler o valor do id de fora da classe.
    public void setId(Long id) { this.id = id; }
    // Setter para o id. Permite alterar o valor do id de fora da classe.

    public String getNome() { return nome; } // Getter para o nome do projeto.
    public void setNome(String nome) { this.nome = nome; }
    // Setter para o nome do projeto.

    public String getImageUrl() { return imageUrl; }
    // Getter para a URL da imagem principal.
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    // Setter para a URL da imagem principal.

    public String getDescricao() { return descricao; }
    // Getter para a descrição do projeto.
    public void setDescricao(String descricao) { this.descricao = descricao; }
    // Setter para a descrição do projeto.
    public List<String> getGaleria() { return galeria; }
    // Getter para a galeria de imagens.
    public void setGaleria(List<String> galeria) { this.galeria = galeria; }
    // Setter para a galeria de imagens.
}