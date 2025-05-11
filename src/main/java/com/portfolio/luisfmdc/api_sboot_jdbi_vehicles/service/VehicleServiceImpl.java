package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper.MaintenanceMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper.VehicleMapper;
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

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ResponseEntity<VehicleResponse> createVehicle(VehicleRequest request) {
        Veiculo veiculo = VehicleMapper.toEntity(request);
        Integer id = vehicleRepository.insertNewVehicle(veiculo);
        veiculo.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(VehicleMapper.toResponse(veiculo));
    }

    @Override
    public ResponseEntity<MaintenanceResponse> registerMaintenance(Integer vehicleId, MaintenanceRequest maintenanceRequest) {
        Manutencao manutencao = MaintenanceMapper.toEntity(vehicleId, maintenanceRequest);
        Integer id = vehicleRepository.insertNewMaintenance(manutencao);
        manutencao.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(MaintenanceMapper.toResponse(manutencao));
    }

    @Override
    public ResponseEntity<VehicleResponse> findVehicle(Integer vehicleId) {
        Optional<Veiculo> optionalVeiculo = vehicleRepository.findVehicle(vehicleId);

        if (optionalVeiculo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Veiculo veiculo = optionalVeiculo.get();
        return ResponseEntity.status(HttpStatus.OK).body(VehicleMapper.toResponse(veiculo));
    }

    @Override
    public ResponseEntity<MaintenanceResponse> findMaintenance(Integer maintenanceId) {
        Optional<Manutencao> optionalManutencao = vehicleRepository.findMaintenance(maintenanceId);

        if (optionalManutencao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Manutencao manutencao = optionalManutencao.get();
        return ResponseEntity.status(HttpStatus.OK).body(MaintenanceMapper.toResponse(manutencao));
    }
}
