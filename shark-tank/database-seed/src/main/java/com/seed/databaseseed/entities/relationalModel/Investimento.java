package com.seed.databaseseed.entities.relationalModel;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Investimento implements Serializable {
    @EmbeddedId
    private SharkProjetoPK id = new SharkProjetoPK();
    @Column(name = "valor_do_investimento")
    private Double valorDoInvestimento;
    @Column(name = "porcentagem_vendida_do_projeto")
    private Double porcentagemVendidaDoProjeto;

    public Investimento() {
    }

    public Investimento(Shark shark, Projeto projeto, Double valorDoInvestimento, Double porcentagemVendidaDoProjeto) {
        id.setProjeto(projeto);
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
        Investimento that = (Investimento) o;
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