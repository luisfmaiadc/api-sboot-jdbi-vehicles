package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Manutencao {

    private Integer id;
    private Integer idVeiculo;
    private String descricao;
    private Double custo;
    private LocalDate dataManutencao;

    public Manutencao(Integer idVeiculo, String descricao, Double custo, LocalDate dataManutencao) {
        this.idVeiculo = idVeiculo;
        this.descricao = descricao;
        this.custo = custo;
        this.dataManutencao = dataManutencao;
    }
}
