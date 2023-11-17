package com.seed.databaseseed.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Embeddable
public class SharkProjetoPK {
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "shark_id")
    private Shark shark;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public Shark getShark() {
        return shark;
    }

    public void setShark(Shark shark) {
        this.shark = shark;
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
        SharkProjetoPK that = (SharkProjetoPK) o;
        return Objects.equals(getShark(), that.getShark()) && Objects.equals(getProjeto(), that.getProjeto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShark(), getProjeto());
    }
}
