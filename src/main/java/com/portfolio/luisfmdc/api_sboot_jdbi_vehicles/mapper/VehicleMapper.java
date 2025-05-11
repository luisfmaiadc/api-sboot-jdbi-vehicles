package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Veiculo;
import com.portfolio.luisfmdc.model.VehicleRequest;
import com.portfolio.luisfmdc.model.VehicleResponse;

public class VehicleMapper {

    public static Veiculo toEntity(VehicleRequest request) {
        return new Veiculo(
                request.getFabricante(),
                request.getModelo(),
                request.getPlaca(),
                request.getAno()
        );
    }

    public static VehicleResponse toResponse(Veiculo veiculo) {
        return new VehicleResponse()
                .id(veiculo.getId())
                .fabricante(veiculo.getFabricante())
                .modelo(veiculo.getModelo())
                .placa(veiculo.getPlaca())
                .ano(veiculo.getAnoFabricacao());
    }
}
