package com.portfolio.luisfmdc.api_sboot_jdbi_vehicles.config.exception;

import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VehicleExceptionHandler {

    @ExceptionHandler(UnableToExecuteStatementException.class)
    public ResponseEntity handleUnableToExecuteStatementException() {
        return ResponseEntity.badRequest().build();
    }

}
