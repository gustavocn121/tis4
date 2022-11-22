package br.com.pucminas.hospital.exceptions;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;

}
