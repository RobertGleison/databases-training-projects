package com.seed.databaseseed.entities.relationalModel;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Projeto")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projeto_id")
    private Integer id;
    @Column(name = "nome")
    private String name;
    private String website;
    @Column(name = "valor_de_mercado")
    private Double valuation;
    @Column(name = "categoria")
    private String category;
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_do_episodio")
    private Episode episode;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Entrepeneur> entrepeneurs = new ArrayList<>();
    @OneToMany(mappedBy = "id.project", cascade = CascadeType.ALL)
    private List<Investment> investments = new ArrayList<>();

    public Project(Integer id, String name, String website, Double valuation, String category, String description) {
        this.id = id;
        this.name = name;
        this.website = website;
        this.valuation = valuation;
        this.category = category;
        this.description = description;
    }

    public Project(Integer id, String name, String website, Double valuation, String category, String description, Episode episode) {
        this.id = id;
        this.name = name;
        this.website = website;
        this.valuation = valuation;
        this.category = category;
        this.description = description;
        this.episode = episode;
    }

    public Project() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Double getValuation() {
        return valuation;
    }

    public void setValuation(Double valuation) {
        this.valuation = valuation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Episode getEpisode() { return episode;
    }

    public void setEpisode(Episode episode) { this.episode = episode; }

    public List<Investment> getInvestments() { return investments; }

    public void setInvestments(List<Investment> investments) { this.investments = investments; }

    public List<Entrepeneur> getEntrepeneurs() {
        return entrepeneurs;
    }

    public void setEntrepeneurs(List<Entrepeneur> entrepeneurs) {
        this.entrepeneurs = entrepeneurs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
