package com.seed.databaseseed.entities.relationalModel;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Empreendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empreendedor_id")
    private Integer id;
    private String nome;
    private String genero;
    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public Empreendedor(Integer id, String nome, String genero, Projeto projeto) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.projeto = projeto;
    }

    public Empreendedor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empreendedor that = (Empreendedor) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Empreendedor{" +
                "Id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", projeto=" + projeto +
                '}';
    }
}
