package com.seed.databaseseed.dtos;

import com.seed.databaseseed.entities.Projeto;
import com.seed.databaseseed.entities.Shark;

import java.util.Set;

public record EpisodioDto(Integer numero, Integer temporada, Set<Shark> sharks, Set<Projeto> projetos) {
}
