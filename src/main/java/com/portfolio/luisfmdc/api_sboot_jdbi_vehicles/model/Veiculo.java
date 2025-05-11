package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    private Integer id;
    private String fabricante;
    private String modelo;
    private String placa;
    private Integer anoFabricacao;

    public Veiculo(String fabricante, String modelo, String placa, Integer anoFabricacao) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
    }
}
