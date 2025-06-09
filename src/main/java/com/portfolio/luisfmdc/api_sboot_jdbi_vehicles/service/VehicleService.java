package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service;

import com.portfolio.luisfmdc.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {

    ResponseEntity<VehicleResponse> createVehicle(VehicleRequest vehicleRequest);
    ResponseEntity<MaintenanceResponse> registerMaintenance(Integer vehicleId, MaintenanceRequest maintenanceRequest);
    ResponseEntity<VehicleResponse> findVehicle(Integer vehicleId);
    ResponseEntity<MaintenanceResponse> findMaintenance(Integer maintenanceId);
    ResponseEntity<List<MaintenanceResponse>> findMaintenanceByVehicleId(Integer vehicleId);
    ResponseEntity<VehicleResponse> updateVehicle(Integer vehicleId, VehicleRequest vehicleRequest);
    ResponseEntity<MaintenanceResponse> updateMaintenance(Integer maintenanceId, MaintenanceUpdateRequest request);
    ResponseEntity<List<WorkshopResponse>> findWorkshop(String cidade, String estado, String especialidade, String fabricante);
}
