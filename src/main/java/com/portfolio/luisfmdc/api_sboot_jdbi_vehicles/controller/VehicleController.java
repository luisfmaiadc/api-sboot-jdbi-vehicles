package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.controller;

import com.portfolio.luisfmdc.api.VehiclesApi;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service.VehicleService;
import com.portfolio.luisfmdc.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleController implements VehiclesApi {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public ResponseEntity<VehicleResponse> createVehicle(VehicleRequest vehicleRequest) {
        return vehicleService.createVehicle(vehicleRequest);
    }

    @Override
    public ResponseEntity<MaintenanceResponse> registerMaintenance(Integer vehicleId, MaintenanceRequest maintenanceRequest) {
        return vehicleService.registerMaintenance(vehicleId, maintenanceRequest);
    }

    @Override
    public ResponseEntity<VehicleResponse> findVehicle(Integer vehicleId) {
        return vehicleService.findVehicle(vehicleId);
    }

    @Override
    public ResponseEntity<MaintenanceResponse> findMaintenance(Integer maintenanceId) {
        return vehicleService.findMaintenance(maintenanceId);
    }

    @Override
    public ResponseEntity<List<MaintenanceResponse>> findMaintenanceByVehicleId(Integer vehicleId) {
        return vehicleService.findMaintenanceByVehicleId(vehicleId);
    }

    @Override
    public ResponseEntity<VehicleResponse> updateVehicle(Integer vehicleId, VehicleRequest vehicleRequest) {
        return vehicleService.updateVehicle(vehicleId, vehicleRequest);
    }

    @Override
    public ResponseEntity<MaintenanceResponse> updateMaintenance(Integer maintenanceId, MaintenanceUpdateRequest request) {
        return vehicleService.updateMaintenance(maintenanceId, request);
    }

    @Override
    public ResponseEntity<List<WorkshopResponse>> findWorkshop(String cidade, String estado, String especialidade, String fabricante) {
        return vehicleService.findWorkshop(cidade, estado, especialidade, fabricante);
    }
}
