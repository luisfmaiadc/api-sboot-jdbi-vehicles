package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.repository;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Manutencao;
import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.model.Veiculo;
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
    int insertNewVehicle(@BindBean Veiculo veiculo);

    @SqlUpdate
    @GetGeneratedKeys
    int insertNewMaintenance(@BindBean Manutencao manutencao);

    @SqlQuery
    @RegisterBeanMapper(Veiculo.class)
    Optional<Veiculo> findVehicle(@Bind("id") Integer id);

    @SqlQuery
    @RegisterBeanMapper(Manutencao.class)
    Optional<Manutencao> findMaintenance(@Bind("id") Integer id);
}
