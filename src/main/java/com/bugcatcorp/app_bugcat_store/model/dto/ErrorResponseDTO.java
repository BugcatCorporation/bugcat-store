package com.bugcatcorp.app_bugcat_store.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ErrorResponseDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private final Date timeStamp = new Date();

    private final int code;
    private final String status;
    private final String stackTrace;
    private final Object message;

    public ErrorResponseDTO(HttpStatus httpStatus, Object message, String stackTrace) {
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.stackTrace = stackTrace;
        this.message = message;
    }
}
