package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Maintenance;
import com.portfolio.luisfmdc.model.MaintenanceRequest;
import com.portfolio.luisfmdc.model.MaintenanceResponse;

import java.time.LocalDate;

public class MaintenanceMapper {

    public static Maintenance toEntity(Integer vehicleId, MaintenanceRequest request) {
        return new Maintenance(
                vehicleId,
                request.getWorkshopId(),
                request.getDescription(),
                request.getCost(),
                LocalDate.now()
        );
    }

    public static MaintenanceResponse toResponse(Maintenance maintenance) {
        return new MaintenanceResponse()
                .id(maintenance.getId())
                .idVeiculo(maintenance.getIdVeiculo())
                .idOficina(maintenance.getIdOficina())
                .descricao(maintenance.getDescricao())
                .custo(maintenance.getCusto())
                .data(maintenance.getDataManutencao())
                .ativa(maintenance.getAtiva());
    }
}
