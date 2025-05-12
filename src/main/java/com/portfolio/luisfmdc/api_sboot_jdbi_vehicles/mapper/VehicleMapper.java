package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Vehicle;
import com.portfolio.luisfmdc.model.VehicleRequest;
import com.portfolio.luisfmdc.model.VehicleResponse;

public class VehicleMapper {

    public static Vehicle toEntity(VehicleRequest request) {
        return new Vehicle(
                request.getFabricante(),
                request.getModelo(),
                request.getPlaca(),
                request.getAno()
        );
    }

    public static VehicleResponse toResponse(Vehicle vehicle) {
        return new VehicleResponse()
                .id(vehicle.getId())
                .fabricante(vehicle.getFabricante())
                .modelo(vehicle.getModelo())
                .placa(vehicle.getPlaca())
                .ano(vehicle.getAnoFabricacao());
    }
}
