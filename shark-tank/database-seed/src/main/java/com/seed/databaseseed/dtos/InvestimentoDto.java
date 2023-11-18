package com.seed.databaseseed.dtos;

import com.seed.databaseseed.entities.SharkProjetoPK;

public record InvestimentoDto(SharkProjetoPK id, Double valorDoInvestimento, Double porcentagemVendidaDoProjeto) {
}
