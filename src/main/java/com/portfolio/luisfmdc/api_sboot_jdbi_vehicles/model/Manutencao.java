package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Manutencao {

    private Integer id;
    private Integer idVeiculo;
    private String descricao;
    private BigDecimal custo;
    private LocalDate dataManutencao;
}
