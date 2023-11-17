package com.seed.databaseseed.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nome;
    private String localidade;
    private String website;
    @Column(name = "valor_de_mercado")
    private Double valorDeMercado;
    private String categoria;
}
