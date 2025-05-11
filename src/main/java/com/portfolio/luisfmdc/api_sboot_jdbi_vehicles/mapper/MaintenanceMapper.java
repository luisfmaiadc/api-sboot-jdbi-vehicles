package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Manutencao;
import com.portfolio.luisfmdc.model.MaintenanceRequest;
import com.portfolio.luisfmdc.model.MaintenanceResponse;

import java.time.LocalDate;

public class MaintenanceMapper {

    public static Manutencao toEntity(Integer vehicleId, MaintenanceRequest request) {
        return new Manutencao(
                vehicleId,
                request.getDescription(),
                request.getCost(),
                LocalDate.now()
        );
    }

    public static MaintenanceResponse toResponse(Manutencao manutencao) {
        return new MaintenanceResponse()
                .id(manutencao.getId())
                .idVeiculo(manutencao.getIdVeiculo())
                .descricao(manutencao.getDescricao())
                .custo(manutencao.getCusto())
                .data(manutencao.getDataManutencao());
    }
}
