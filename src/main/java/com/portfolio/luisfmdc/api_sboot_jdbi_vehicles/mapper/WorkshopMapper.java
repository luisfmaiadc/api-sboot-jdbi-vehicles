package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Workshop;
import com.portfolio.luisfmdc.model.WorkshopResponse;

import java.util.List;

public class WorkshopMapper {

    public static List<Workshop> toEntityList(List<WorkshopResponse> response) {
        return response.stream()
                .map(w -> new Workshop(
                        w.getId(),
                        w.getNome(),
                        w.getCnpj(),
                        w.getCidade(),
                        w.getEstado(),
                        w.getAtiva(),
                        ManufacturerMapper.toEntityList(w.getFabricante()),
                        SpecialtyMapper.toEntityList(w.getEspecialidade()))
                ).toList();
    }
}
