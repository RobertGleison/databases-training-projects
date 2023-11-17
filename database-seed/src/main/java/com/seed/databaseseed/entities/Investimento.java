package com.seed.databaseseed.entities;

import java.util.Objects;

public class Investimento {
    private SharkProjetoPK id;
    private Double valorDoInvestimento;
    private Double porcentagemVendidaDoProjeto;

    public Investimento(Shark shark, Projeto projeto, Double valorDoInvestimento, Double porcentagemVendidaDoProjeto) {
        this.id.setProjeto(projeto);
        this.id.setShark(shark);
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

    public Shark getShark() {
        return id.getShark();
    }

    public void setShark(Shark shark) {
        id.setShark(shark);
    }

    public Projeto getProjeto() {
        return id.getProjeto();
    }

    public void setProjeto(Projeto projeto) {
        id.setProjeto(projeto);
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
