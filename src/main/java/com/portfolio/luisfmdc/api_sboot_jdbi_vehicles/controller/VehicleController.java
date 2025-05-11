package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.controller;

import com.portfolio.luisfmdc.api.VehiclesApi;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service.VehicleService;
import com.portfolio.luisfmdc.model.VehicleRequest;
import com.portfolio.luisfmdc.model.VehicleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
