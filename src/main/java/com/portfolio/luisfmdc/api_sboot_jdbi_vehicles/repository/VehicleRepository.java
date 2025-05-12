package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.repository;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Maintenance;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Vehicle;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@UseClasspathSqlLocator
public interface VehicleRepository {

    @SqlUpdate
    @GetGeneratedKeys
    int insertNewVehicle(@BindBean Vehicle vehicle);

    @SqlUpdate
    @GetGeneratedKeys
    int insertNewMaintenance(@BindBean Maintenance maintenance);

    @SqlQuery
    @RegisterBeanMapper(Vehicle.class)
    Optional<Vehicle> findVehicle(@Bind("id") Integer id);

    @SqlQuery
    @RegisterBeanMapper(Maintenance.class)
    Optional<Maintenance> findMaintenance(@Bind("id") Integer id);

    @SqlQuery
    boolean activeMaintenance(@Bind("vehicleId") Integer vehicleId);
}
