package com.seed.databaseseed.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Shark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shark_id")
    private Integer id;
    public String nome;
    @ManyToMany(mappedBy = "sharks")
    private Set<Episodio> episodios = new HashSet<>();
    List<Investimento> investimentos = new ArrayList<>();

    public Shark(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public Set<Episodio> getEpisodio() {
        return episodios;
    }

    public void setEpisodio(Set<Episodio> episodios) {
        this.episodios = episodios;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shark shark = (Shark) o;
        return Objects.equals(nome, shark.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Shark{" +
                "Id=" + id +
                ", nome='" + nome + '\'' +
                ", episodios=" + episodios +
                ", investimentos=" + investimentos +
                '}';
    }
}
