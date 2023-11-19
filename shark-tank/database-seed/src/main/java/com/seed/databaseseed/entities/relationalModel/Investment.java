package com.seed.databaseseed.entities.relationalModel;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Investimento")
public class Investment implements Serializable {
    @EmbeddedId
    private SharkProjectPK id = new SharkProjectPK();
    @Column(name = "valor_do_investimento")
    private Double valorDoInvestimento;
    @Column(name = "porcentagem_vendida_do_projeto")
    private Double porcentagemVendidaDoProjeto;

    public Investment() {
    }

    public Investment(Shark shark, Project project, Double valorDoInvestimento, Double porcentagemVendidaDoProjeto) {
        id.setProject(project);
        id.setShark(shark);
        this.valorDoInvestimento = valorDoInvestimento;
        this.porcentagemVendidaDoProjeto = porcentagemVendidaDoProjeto;
    }

    public Double getValorDoInvestimento() {
        return valorDoInvestimento;
    }

    public void setValorDoInvestimento(Double valorDoInvestimento) {
        this.valorDoInvestimento = valorDoInvestimento;
    }

    public Double getPorcentagemVendidaDoProjeto() {
        return porcentagemVendidaDoProjeto;
    }

    public void setPorcentagemVendidaDoProjeto(Double porcentagemVendidaDoProjeto) {
        this.porcentagemVendidaDoProjeto = porcentagemVendidaDoProjeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investment that = (Investment) o;
        return Objects.equals(getValorDoInvestimento(), that.getValorDoInvestimento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValorDoInvestimento());
    }

    @Override
    public String toString() {
        return "Investimento{" +
                "id=" + id +
                ", valorDoInvestimento=" + valorDoInvestimento +
                ", porcentagemVendidaDoProjeto=" + porcentagemVendidaDoProjeto +
                '}';
    }
}