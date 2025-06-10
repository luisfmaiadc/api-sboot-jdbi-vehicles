package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {

    @EqualsAndHashCode.Include
    private Integer id;

    private String fabricante;
    private Boolean ativa;
}