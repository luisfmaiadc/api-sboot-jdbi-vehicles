package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper.MaintenanceMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper.VehicleMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Maintenance;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Vehicle;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.repository.VehicleRepository;
import com.portfolio.luisfmdc.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ResponseEntity<VehicleResponse> createVehicle(VehicleRequest request) {
        Vehicle vehicle = VehicleMapper.toEntity(request);
        Integer id = vehicleRepository.insertNewVehicle(vehicle);
        vehicle.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(VehicleMapper.toResponse(vehicle));
    }

    @Override
    public ResponseEntity<MaintenanceResponse> registerMaintenance(Integer vehicleId, MaintenanceRequest maintenanceRequest) {
        boolean isRegisterMaintenanceValid = isRegisterMaintenanceValid(vehicleId);
        if (!isRegisterMaintenanceValid) {
            return ResponseEntity.badRequest().build();
        }

        Maintenance maintenance = MaintenanceMapper.toEntity(vehicleId, maintenanceRequest);
        Integer id = vehicleRepository.insertNewMaintenance(maintenance);
        maintenance.setId(id);
        maintenance.setAtiva(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(MaintenanceMapper.toResponse(maintenance));
    }

    private boolean isRegisterMaintenanceValid(Integer vehicleId) {
        Optional<Vehicle> optionalVeiculo = vehicleRepository.findVehicle(vehicleId);
        if (optionalVeiculo.isEmpty()) {
            return false;
        }
        return !vehicleRepository.activeMaintenance(vehicleId);
    }

    @Override
    public ResponseEntity<VehicleResponse> findVehicle(Integer vehicleId) {
        Optional<Vehicle> optionalVeiculo = vehicleRepository.findVehicle(vehicleId);

        if (optionalVeiculo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Vehicle vehicle = optionalVeiculo.get();
        return ResponseEntity.status(HttpStatus.OK).body(VehicleMapper.toResponse(vehicle));
    }

    @Override
    public ResponseEntity<MaintenanceResponse> findMaintenance(Integer maintenanceId) {
        Optional<Maintenance> optionalManutencao = vehicleRepository.findMaintenance(maintenanceId);

        if (optionalManutencao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Maintenance maintenance = optionalManutencao.get();
        return ResponseEntity.status(HttpStatus.OK).body(MaintenanceMapper.toResponse(maintenance));
    }

    @Override
    public ResponseEntity<List<MaintenanceResponse>> findMaintenanceByVehicleId(Integer vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicle(vehicleId);
        if (optionalVehicle.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Maintenance> maintenanceList = vehicleRepository.findMaintenancesByVehicle(vehicleId);

        if (maintenanceList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<MaintenanceResponse> maintenanceResponseList = new ArrayList<>();

        for (Maintenance maintenance : maintenanceList) {
            maintenanceResponseList.add(MaintenanceMapper.toResponse(maintenance));
        }

        return ResponseEntity.ok(maintenanceResponseList);
    }

    @Override
    public ResponseEntity<VehicleResponse> updateVehicle(Integer vehicleId, VehicleRequest vehicleRequest) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicle(vehicleId);
        if (optionalVehicle.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Vehicle vehicle = optionalVehicle.get();
        boolean isUpdateValid = vehicle.updateVehicle(vehicleRequest);
        if (isUpdateValid) {
            vehicleRepository.updateVehicle(vehicle);
        }
        return ResponseEntity.ok(VehicleMapper.toResponse(vehicle));
    }

    @Override
    public ResponseEntity<MaintenanceResponse> updateMaintenance(Integer maintenanceId, MaintenanceUpdateRequest request) {
        Optional<Maintenance> optionalMaintenance = vehicleRepository.findMaintenance(maintenanceId);
        if (optionalMaintenance.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Maintenance maintenance = optionalMaintenance.get();
        boolean isUpdateValid = maintenance.updateMaintenance(request);
        if (isUpdateValid) {
            vehicleRepository.updateMaintenance(maintenance);
        }
        return  ResponseEntity.ok(MaintenanceMapper.toResponse(maintenance));
    }
}
