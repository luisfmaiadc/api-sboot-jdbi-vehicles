package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model;

import com.portfolio.luisfmdc.model.VehicleRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private Integer id;
    private String fabricante;
    private String modelo;
    private String placa;
    private Integer anoFabricacao;

    public Vehicle(String fabricante, String modelo, String placa, Integer anoFabricacao) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
    }

    public void updateVehicle(VehicleRequest vehicleRequest) {
        if (vehicleRequest.getFabricante() != null && !vehicleRequest.getFabricante().isBlank()) {
            this.fabricante = vehicleRequest.getFabricante();
        }

        if (vehicleRequest.getModelo() != null && !vehicleRequest.getModelo().isBlank()) {
            this.modelo = vehicleRequest.getModelo();
        }

        if (vehicleRequest.getPlaca() != null && !vehicleRequest.getPlaca().isBlank()) {
            this.placa = vehicleRequest.getPlaca();
        }

        if (vehicleRequest.getAno() != null) {
            this.anoFabricacao = vehicleRequest.getAno();
        }
    }
}
