package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Specialty;
import com.portfolio.luisfmdc.model.WorkshopSpecialtyResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class SpecialtyMapper {

    public static List<Specialty> toEntityList(List<WorkshopSpecialtyResponse> response) {
        return response.stream()
                .map(s -> new Specialty(
                        s.getIdEspecialidade(),
                        s.getEspecialidade(),
                        s.getAtiva())
                ).toList();
    }

    public static Specialty toEntity(WorkshopSpecialtyResponse response) {
        return new Specialty (
                response.getIdEspecialidade(),
                response.getEspecialidade(),
                response.getAtiva());
    }
}
