package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Manufacturer;
import com.portfolio.luisfmdc.model.WorkshopManufacturerResponse;

import java.util.List;

public class ManufacturerMapper {

    public static List<Manufacturer> toEntityList(List<WorkshopManufacturerResponse> response) {
        return response.stream()
                .map(m -> new Manufacturer(
                        m.getIdFabricante(),
                        m.getFabricante(),
                        m.getAtiva()
                )).toList();
    }
}
