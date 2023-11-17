package com.seed.databaseseed.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_do_episodio")
    private Integer numero;
    private Integer temporada;
    @ManyToMany
    @JoinTable(name = "episodio_shark",
            joinColumns = @JoinColumn(name = "shark_id"),
            inverseJoinColumns = @JoinColumn(name = "numero_do_episodio"))
    private Set<Shark> sharks = new HashSet<>();
    @OneToMany(mappedBy = "episodio")
    private Set<Projeto> projetos = new HashSet<>();

    public Episodio(Integer numero, Integer temporada) {
        this.numero = numero;
        this.temporada = temporada;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Set<Shark> getSharks() {
        return sharks;
    }

    public void setSharks(Set<Shark> sharks) {
        this.sharks = sharks;
    }

    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episodio episodio = (Episodio) o;
        return Objects.equals(numero, episodio.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "numero=" + numero +
                ", temporada=" + temporada +
                ", sharks=" + sharks +
                ", projetos=" + projetos +
                '}';
    }
}
