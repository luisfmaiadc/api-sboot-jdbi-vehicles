package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper.MaintenanceMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper.VehicleMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Maintenance;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Vehicle;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.repository.VehicleRepository;
import com.portfolio.luisfmdc.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    private Vehicle vehicle;

    private VehicleRequest vehicleRequest;

    private Maintenance maintenance;

    private MaintenanceRequest maintenanceRequest;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle(1, "Ford", "Mustang", "ABC7H09", 2025);
        vehicleRequest = new VehicleRequest("Ford", "Mustang", "ABC7H09", 2025);
        maintenance = new Maintenance(1, 1, 1, "Troca de óleo.", 30.00, LocalDate.of(2025, 5, 17), false);
        maintenanceRequest = new MaintenanceRequest(1, "Troca de óleo", 30.00, 1);
    }

    @Test
    void createVehicle() {
        Vehicle expectedVehicle = VehicleMapper.toEntity(vehicleRequest);
        when(vehicleRepository.insertNewVehicle(expectedVehicle)).thenReturn(1);

        ResponseEntity<VehicleResponse> response = vehicleService.createVehicle(vehicleRequest);
        expectedVehicle.setId(1);

        assertEquals(VehicleMapper.toResponse(expectedVehicle), response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

//    @Test
//    void registerMaintenance() {
//        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.of(vehicle));
//        when(vehicleRepository.activeMaintenance(vehicle.getId())).thenReturn(false);
//        Maintenance expectedMaintenance = MaintenanceMapper.toEntity(vehicle.getId(), maintenanceRequest);
//        when(vehicleRepository.insertNewMaintenance(expectedMaintenance)).thenReturn(1);
//
//        ResponseEntity<MaintenanceResponse> response = vehicleService.registerMaintenance(1, maintenanceRequest);
//        expectedMaintenance.setId(1);
//        expectedMaintenance.setAtiva(true);
//
//        assertEquals(MaintenanceMapper.toResponse(expectedMaintenance), response.getBody());
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//    }

    @Test
    void vehicleNotFound() {
        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.empty());
        ResponseEntity<MaintenanceResponse> response = vehicleService.registerMaintenance(1, maintenanceRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

//    @Test
//    void vehicleActiveMaintenance() {
//        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.of(vehicle));
//        when(vehicleRepository.activeMaintenance(vehicle.getId())).thenReturn(true);
//
//        ResponseEntity<MaintenanceResponse> response = vehicleService.registerMaintenance(1, maintenanceRequest);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }

    @Test
    void findVehicle() {
        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.of(vehicle));
        VehicleResponse vehicleResponse = VehicleMapper.toResponse(vehicle);

        ResponseEntity<VehicleResponse> response = vehicleService.findVehicle(1);

        assertEquals(vehicleResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundVehicle() {
        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.empty());
        ResponseEntity<VehicleResponse> response = vehicleService.findVehicle(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findMaintenance() {
        when(vehicleRepository.findMaintenance(1)).thenReturn(Optional.of(maintenance));
        MaintenanceResponse maintenanceResponse = MaintenanceMapper.toResponse(maintenance);

        ResponseEntity<MaintenanceResponse> response = vehicleService.findMaintenance(1);

        assertEquals(maintenanceResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundMaintenance() {
        when(vehicleRepository.findMaintenance(1)).thenReturn(Optional.empty());
        ResponseEntity<MaintenanceResponse> response = vehicleService.findMaintenance(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findMaintenanceByVehicleId() {
        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.of(vehicle));
        List<Maintenance> maintenanceList = List.of(maintenance, maintenance);
        when(vehicleRepository.findMaintenancesByVehicle(vehicle.getId())).thenReturn(maintenanceList);

        ResponseEntity<List<MaintenanceResponse>> response = vehicleService.findMaintenanceByVehicleId(1);

        assertEquals(MaintenanceMapper.toResponse(maintenanceList.get(0)), response.getBody().get(0));
        assertEquals(MaintenanceMapper.toResponse(maintenanceList.get(1)), response.getBody().get(1));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void emptyFindMaintenanceByVehicleId() {
        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.of(vehicle));
        List<Maintenance> maintenanceList = new ArrayList<>();
        when(vehicleRepository.findMaintenancesByVehicle(vehicle.getId())).thenReturn(maintenanceList);

        ResponseEntity<List<MaintenanceResponse>> response = vehicleService.findMaintenanceByVehicleId(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void notFoundVehicleFindMaintenanceByVehicleId() {
        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.empty());
        ResponseEntity<List<MaintenanceResponse>> response = vehicleService.findMaintenanceByVehicleId(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateVehicle() {
        Vehicle testeVehicle = spy(Vehicle.class);
        VehicleRequest testeVehicleRequest = new VehicleRequest();
        testeVehicleRequest.setModelo("Ka");
        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.of(testeVehicle));

        ResponseEntity<VehicleResponse> response = vehicleService.updateVehicle(1, testeVehicleRequest);

        assertEquals(VehicleMapper.toResponse(testeVehicle), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundUpdateVehicle() {
        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.empty());
        ResponseEntity<VehicleResponse> response = vehicleService.updateVehicle(1, vehicleRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void notValidUpdateVehicle() {
        Vehicle testeVehicle = mock(Vehicle.class);
        testeVehicle.setFabricante("Ford");
        when(vehicleRepository.findVehicle(1)).thenReturn(Optional.of(testeVehicle));

        ResponseEntity<VehicleResponse> response = vehicleService.updateVehicle(1, vehicleRequest);

        assertEquals(VehicleMapper.toResponse(testeVehicle), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateMaintenance() {
        Maintenance testeMaintenance = spy(Maintenance.class);
        MaintenanceUpdateRequest testeMaintenanceRequest = new MaintenanceUpdateRequest();
        testeMaintenanceRequest.setCost(40.00);
        when(vehicleRepository.findMaintenance(1)).thenReturn(Optional.of(testeMaintenance));

        ResponseEntity<MaintenanceResponse> response = vehicleService.updateMaintenance(1, testeMaintenanceRequest);

        assertEquals(MaintenanceMapper.toResponse(testeMaintenance), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundUpdateMaintenance() {
        when(vehicleRepository.findMaintenance(1)).thenReturn(Optional.empty());
        ResponseEntity<MaintenanceResponse> response = vehicleService.updateMaintenance(1, new MaintenanceUpdateRequest());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void notValidUpdateMaintenance() {
        Maintenance testeMaintenance = mock(Maintenance.class);
        when(vehicleRepository.findMaintenance(1)).thenReturn(Optional.of(testeMaintenance));

        ResponseEntity<MaintenanceResponse> response = vehicleService.updateMaintenance(1, new MaintenanceUpdateRequest());

        assertEquals(MaintenanceMapper.toResponse(testeMaintenance), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}