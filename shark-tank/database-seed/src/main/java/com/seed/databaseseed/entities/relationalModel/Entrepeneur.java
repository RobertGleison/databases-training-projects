package com.seed.databaseseed.entities.relationalModel;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Empreendedor")
public class Entrepeneur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empreendedor_id")
    private Integer id;
    @Column(name = "nome")
    private String name;
    @Column(name = "genero")
    private String gender;
    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Project project;

    public Entrepeneur(Integer id, String name, String gender, Project project) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.project = project;
    }

    public Entrepeneur() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrepeneur that = (Entrepeneur) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Empreendedor{" +
                "Id=" + id +
                ", nome='" + name + '\'' +
                ", genero='" + gender + '\'' +
                ", projeto=" + project +
                '}';
    }
}
