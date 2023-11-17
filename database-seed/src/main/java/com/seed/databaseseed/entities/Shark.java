package com.seed.databaseseed.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.*;

@Entity

public class Shark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    public String nome;
    private Set<Episodio> episodios = new HashSet<>();
    List<Investimento> investimentos = new ArrayList<>();

    public Shark(Integer id, String nome) {
        Id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", episodios=" + episodios +
                ", investimentos=" + investimentos +
                '}';
    }
}
