package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model;

import com.portfolio.luisfmdc.model.MaintenanceUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    private Integer id;
    private Integer idVeiculo;
    private String descricao;
    private Double custo;
    private LocalDate dataManutencao;
    private Boolean ativa;

    public Maintenance(Integer idVeiculo, String descricao, Double custo, LocalDate dataManutencao) {
        this.idVeiculo = idVeiculo;
        this.descricao = descricao;
        this.custo = custo;
        this.dataManutencao = dataManutencao;
    }

    public boolean updateMaintenance(MaintenanceUpdateRequest updateRequest) {
        boolean isUpdateValid = false;
        if (updateRequest.getDescription() != null && !updateRequest.getDescription().isBlank()) {
            if (!Objects.equals(updateRequest.getDescription(), this.descricao)) {
                this.descricao = updateRequest.getDescription();
                isUpdateValid = true;
            }
        }

        if (updateRequest.getCost() != null && updateRequest.getCost() > 0.0) {
            if (!Objects.equals(updateRequest.getCost(), this.custo)) {
                this.custo = updateRequest.getCost();
                isUpdateValid = true;
            }
        }

        if (updateRequest.getAtiva() != null) {
            if (updateRequest.getAtiva() != this.ativa) {
                this.ativa = updateRequest.getAtiva();
                isUpdateValid = true;
            }
        }
        return isUpdateValid;
    }
}
