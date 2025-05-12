package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service;

import com.portfolio.luisfmdc.model.MaintenanceRequest;
import com.portfolio.luisfmdc.model.MaintenanceResponse;
import com.portfolio.luisfmdc.model.VehicleRequest;
import com.portfolio.luisfmdc.model.VehicleResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {

    ResponseEntity<VehicleResponse> createVehicle(VehicleRequest vehicleRequest);
    ResponseEntity<MaintenanceResponse> registerMaintenance(Integer vehicleId, MaintenanceRequest maintenanceRequest);
    ResponseEntity<VehicleResponse> findVehicle(Integer vehicleId);
    ResponseEntity<MaintenanceResponse> findMaintenance(Integer maintenanceId);
    ResponseEntity<List<MaintenanceResponse>> findMaintenanceByVehicleId(Integer vehicleId);
}
