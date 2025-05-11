package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service;

import com.portfolio.luisfmdc.model.VehicleRequest;
import com.portfolio.luisfmdc.model.VehicleResponse;
import org.springframework.http.ResponseEntity;

public interface VehicleService {

    ResponseEntity<VehicleResponse> createVehicle(VehicleRequest vehicleRequest);
}
