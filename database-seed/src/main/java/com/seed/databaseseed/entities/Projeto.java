package com.seed.databaseseed.entities;

import jakarta.persistence.*;


import java.util.*;


@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projeto_id")
    private Integer id;
    private String nome;
    private String localidade;
    private String website;
    @Column(name = "valor_de_mercado")
    private Double valorDeMercado;
    private String categoria;
    private String descricao;
    @Column(name = "numero_do_episodio")
    private Episodio episodio;
    private Set<Empreendedor> empreendedores = new HashSet<>();
    private List<Investimento> investimentos = new ArrayList<>();

    public Projeto(Integer id, String nome, String localidade, String website, Double valorDeMercado, String categoria, String descricao, Episodio episodio) {
        this.id = id;
        this.nome = nome;
        this.localidade = localidade;
        this.website = website;
        this.valorDeMercado = valorDeMercado;
        this.categoria = categoria;
        this.descricao = descricao;
        this.episodio = episodio;
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

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Double getValorDeMercado() {
        return valorDeMercado;
    }

    public void setValorDeMercado(Double valorDeMercado) {
        this.valorDeMercado = valorDeMercado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Episodio getEpisodio() {
        return episodio;
    }

    public void setEpisodio(Episodio episodio) {
        this.episodio = episodio;
    }

    public Set<Empreendedor> getEmpreendedores() {
        return empreendedores;
    }

    public void setEmpreendedores(Set<Empreendedor> empreendedores) {
        this.empreendedores = empreendedores;
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
        Projeto projeto = (Projeto) o;
        return Objects.equals(id, projeto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localidade='" + localidade + '\'' +
                ", website='" + website + '\'' +
                ", valorDeMercado=" + valorDeMercado +
                ", categoria='" + categoria + '\'' +
                ", descricao='" + descricao + '\'' +
                ", episodio=" + episodio +
                ", empreendedores=" + empreendedores +
                ", investimentos=" + investimentos +
                '}';
    }
}
