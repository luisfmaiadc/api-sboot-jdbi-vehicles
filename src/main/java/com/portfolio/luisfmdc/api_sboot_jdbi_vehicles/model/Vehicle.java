package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model;

import com.portfolio.luisfmdc.model.VehicleRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    public boolean updateVehicle(VehicleRequest vehicleRequest) {
        boolean isUpdateValid = false;
        if (vehicleRequest.getFabricante() != null && !vehicleRequest.getFabricante().isBlank()) {
            if (!Objects.equals(vehicleRequest.getFabricante(), this.fabricante)) {
                this.fabricante = vehicleRequest.getFabricante();
                isUpdateValid = true;
            }
        }

        if (vehicleRequest.getModelo() != null && !vehicleRequest.getModelo().isBlank()) {
            if (!Objects.equals(vehicleRequest.getModelo(), this.modelo)) {
                this.modelo = vehicleRequest.getModelo();
                isUpdateValid = true;
            }
        }

        if (vehicleRequest.getPlaca() != null && !vehicleRequest.getPlaca().isBlank()) {
            if (!Objects.equals(vehicleRequest.getPlaca(), this.placa)) {
                this.placa = vehicleRequest.getPlaca();
                isUpdateValid = true;
            }
        }

        if (vehicleRequest.getAno() != null && vehicleRequest.getAno() >= 1901) {
            if (!Objects.equals(vehicleRequest.getAno(), this.anoFabricacao)) {
                this.anoFabricacao = vehicleRequest.getAno();
                isUpdateValid = true;
            }
        }
        return isUpdateValid;
    }
}
