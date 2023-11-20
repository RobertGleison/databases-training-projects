package com.seed.databaseseed.entities.relationalModel;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Shark {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shark_id")
    private Integer id;
    @Column(name = "nome")
    private String name;
    @ManyToMany(mappedBy = "sharks", cascade = CascadeType.ALL)
    private Set<Episode> episodes = new HashSet<>();
    @OneToMany(mappedBy = "id.shark", cascade = CascadeType.ALL)
    private List<Investment> investments = new ArrayList<>();

    public Shark(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Shark() {
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

    public Set<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

    public void addInvestment(Investment investment) {
        investments.add(investment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shark shark = (Shark) o;
        return Objects.equals(name, shark.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
