package com.seed.databaseseed.dtos;

import com.seed.databaseseed.entities.Projeto;

import java.util.Set;

public record EmpreendedorDto(Integer id, String nome, String genero, Set<Projeto> projetos) {
}
