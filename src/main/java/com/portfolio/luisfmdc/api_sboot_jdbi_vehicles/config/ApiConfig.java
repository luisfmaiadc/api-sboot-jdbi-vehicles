package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.config;

import com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.repository.VehicleRepository;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class ApiConfig {

    @Value("${workshop.service.url}")
    private String workshopServiceUrl;

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    @Bean
    public VehicleRepository vehicleRepository(Jdbi jdbi) {
        return jdbi.onDemand(VehicleRepository.class);
    }

    @Bean
    @Qualifier("workshopRestTemplate")
    public RestTemplate workshopRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(workshopServiceUrl).build();
    }
}
