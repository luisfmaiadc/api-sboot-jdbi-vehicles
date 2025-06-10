package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    private Integer id;
    private Integer idVeiculo;
    private Integer idOficina;
    private String descricao;
    private Double custo;
    private LocalDate dataManutencao;
    private Boolean ativa;

    public Maintenance(Integer idVeiculo, Integer idOficina, String descricao, Double custo, LocalDate dataManutencao) {
        this.idVeiculo = idVeiculo;
        this.idOficina = idOficina;
        this.descricao = descricao;
        this.custo = custo;
        this.dataManutencao = dataManutencao;
    }
}
