package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Manutencao;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Veiculo;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.repository.VehicleRepository;
import com.portfolio.luisfmdc.model.MaintenanceRequest;
import com.portfolio.luisfmdc.model.MaintenanceResponse;
import com.portfolio.luisfmdc.model.VehicleRequest;
import com.portfolio.luisfmdc.model.VehicleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ResponseEntity<VehicleResponse> createVehicle(VehicleRequest request) {
        Veiculo veiculo = new Veiculo(request.getFabricante(), request.getModelo(), request.getPlaca(), request.getAno());
        Integer id = vehicleRepository.insertNewVehicle(veiculo);
        VehicleResponse vehicleResponse = new VehicleResponse()
                .id(id)
                .fabricante(veiculo.getFabricante())
                .modelo(veiculo.getModelo())
                .placa(veiculo.getPlaca())
                .ano(veiculo.getAnoFabricacao());
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleResponse);
    }

    @Override
    public ResponseEntity<MaintenanceResponse> registerMaintenance(Integer vehicleId, MaintenanceRequest maintenanceRequest) {
        Manutencao manutencao = new Manutencao(vehicleId, maintenanceRequest.getDescription(), maintenanceRequest.getCost(), LocalDate.now());
        Integer id = vehicleRepository.insertNewMaintenance(manutencao);
        MaintenanceResponse maintenanceResponse = new MaintenanceResponse()
                .id(id)
                .idVeiculo(manutencao.getIdVeiculo())
                .descricao(manutencao.getDescricao())
                .custo(manutencao.getCusto())
                .data(manutencao.getDataManutencao());
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceResponse);
    }
}
