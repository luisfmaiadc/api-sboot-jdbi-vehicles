package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.service;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper.MaintenanceMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.mapper.VehicleMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Maintenance;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Vehicle;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.repository.VehicleRepository;
import com.portfolio.luisfmdc.model.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final RestTemplate workshopRestTemplate;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, @Qualifier("workshopRestTemplate") RestTemplate workshopRestTemplate) {
        this.vehicleRepository = vehicleRepository;
        this.workshopRestTemplate = workshopRestTemplate;
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
        if (!isRegisterMaintenanceValid) return ResponseEntity.badRequest().build();

        Maintenance maintenance = MaintenanceMapper.toEntity(vehicleId, maintenanceRequest);
        Integer id = vehicleRepository.insertNewMaintenance(maintenance);
        maintenance.setId(id);
        maintenance.setAtiva(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(MaintenanceMapper.toResponse(maintenance));
    }

    private boolean isRegisterMaintenanceValid(Integer vehicleId) {
        Optional<Vehicle> optionalVeiculo = vehicleRepository.findVehicle(vehicleId);
        if (optionalVeiculo.isEmpty()) return false;
        return !vehicleRepository.activeMaintenance(vehicleId);
    }

    @Override
    public ResponseEntity<VehicleResponse> findVehicle(Integer vehicleId) {
        Optional<Vehicle> optionalVeiculo = vehicleRepository.findVehicle(vehicleId);

        if (optionalVeiculo.isEmpty()) return ResponseEntity.notFound().build();

        Vehicle vehicle = optionalVeiculo.get();
        return ResponseEntity.status(HttpStatus.OK).body(VehicleMapper.toResponse(vehicle));
    }

    @Override
    public ResponseEntity<MaintenanceResponse> findMaintenance(Integer maintenanceId) {
        Optional<Maintenance> optionalManutencao = vehicleRepository.findMaintenance(maintenanceId);

        if (optionalManutencao.isEmpty()) return ResponseEntity.notFound().build();

        Maintenance maintenance = optionalManutencao.get();
        return ResponseEntity.status(HttpStatus.OK).body(MaintenanceMapper.toResponse(maintenance));
    }

    @Override
    public ResponseEntity<List<MaintenanceResponse>> findMaintenanceByVehicleId(Integer vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicle(vehicleId);
        if (optionalVehicle.isEmpty()) return ResponseEntity.badRequest().build();

        List<Maintenance> maintenanceList = vehicleRepository.findMaintenancesByVehicle(vehicleId);
        if (maintenanceList.isEmpty()) return ResponseEntity.noContent().build();

        List<MaintenanceResponse> maintenanceResponseList = maintenanceList.stream().map(MaintenanceMapper::toResponse).toList();
        return ResponseEntity.ok(maintenanceResponseList);
    }

    @Override
    public ResponseEntity<VehicleResponse> updateVehicle(Integer vehicleId, VehicleRequest vehicleRequest) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicle(vehicleId);
        if (optionalVehicle.isEmpty()) return ResponseEntity.notFound().build();

        Vehicle vehicle = optionalVehicle.get();
        boolean isUpdateValid = isVehicleUpdateValid(vehicle, vehicleRequest);
        if (isUpdateValid) {
            vehicleRepository.updateVehicle(vehicle);
        }
        return ResponseEntity.ok(VehicleMapper.toResponse(vehicle));
    }

    private boolean isVehicleUpdateValid(Vehicle vehicle, VehicleRequest vehicleRequest) {
        boolean isUpdateValid = false;
        if (vehicleRequest.getFabricante() != null && !vehicleRequest.getFabricante().isBlank()) {
            if (!Objects.equals(vehicleRequest.getFabricante(), vehicle.getFabricante())) {
                vehicle.setFabricante(vehicleRequest.getFabricante());
                isUpdateValid = true;
            }
        }

        if (vehicleRequest.getModelo() != null && !vehicleRequest.getModelo().isBlank()) {
            if (!Objects.equals(vehicleRequest.getModelo(), vehicle.getModelo())) {
                vehicle.setFabricante(vehicleRequest.getModelo());
                isUpdateValid = true;
            }
        }

        if (vehicleRequest.getAno() != null && vehicleRequest.getAno() >= 1901) {
            if (!Objects.equals(vehicleRequest.getAno(), vehicle.getAnoFabricacao())) {
                vehicle.setAnoFabricacao(vehicleRequest.getAno());
                isUpdateValid = true;
            }
        }
        return isUpdateValid;
    }

    @Override
    public ResponseEntity<MaintenanceResponse> updateMaintenance(Integer maintenanceId, MaintenanceUpdateRequest request) {
        Optional<Maintenance> optionalMaintenance = vehicleRepository.findMaintenance(maintenanceId);
        if (optionalMaintenance.isEmpty()) return ResponseEntity.notFound().build();

        Maintenance maintenance = optionalMaintenance.get();
        boolean isUpdateValid = isUpdateMaintenanceValid(maintenance, request);
        if (isUpdateValid) {
            vehicleRepository.updateMaintenance(maintenance);
        }
        return  ResponseEntity.ok(MaintenanceMapper.toResponse(maintenance));
    }

    private boolean isUpdateMaintenanceValid(Maintenance maintenance, MaintenanceUpdateRequest updateRequest) {
        boolean isUpdateValid = false;
        if (updateRequest.getDescription() != null && !updateRequest.getDescription().isBlank()) {
            if (!Objects.equals(updateRequest.getDescription(), maintenance.getDescricao())) {
                maintenance.setDescricao(updateRequest.getDescription());
                isUpdateValid = true;
            }
        }

        if (updateRequest.getCost() != null && updateRequest.getCost() > 0.0) {
            if (!Objects.equals(updateRequest.getCost(), maintenance.getCusto())) {
                maintenance.setCusto(updateRequest.getCost());
                isUpdateValid = true;
            }
        }

        if (updateRequest.getAtiva() != null) {
            if (updateRequest.getAtiva() != maintenance.getAtiva()) {
                maintenance.setAtiva(updateRequest.getAtiva());
                isUpdateValid = true;
            }
        }
        return isUpdateValid;
    }

    @Override
    public ResponseEntity<List<WorkshopResponse>> findWorkshop(String cidade, String estado, String especialidade, String fabricante) {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            addQueryParam(params, "cidade", cidade);
            addQueryParam(params, "estado", estado);
            addQueryParam(params, "especialidade", especialidade);
            addQueryParam(params, "fabricante", fabricante);

            if (params.isEmpty()) return ResponseEntity.badRequest().build();

            String uri = UriComponentsBuilder
                    .fromPath("/workshop")
                    .queryParams(params)
                    .build()
                    .toUriString();

            ResponseEntity<WorkshopResponse[]> response = workshopRestTemplate.getForEntity(uri, WorkshopResponse[].class);
            if (response.getBody() == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(List.of(response.getBody()));
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.notFound().build();
        } catch (RestClientException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private void addQueryParam(MultiValueMap<String, String> params, String key, String value) {
        if (value != null && !value.isBlank()) {
            params.add(key, value);
        }
    }
}
