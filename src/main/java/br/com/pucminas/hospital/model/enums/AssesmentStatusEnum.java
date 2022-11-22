package br.com.pucminas.hospital.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AssesmentStatusEnum {

    AGENDADO("AGENDADO"),
    CANCELADO("Cancelado"),
    ENVIADO("Enviado"),
    RESPONDIDO("Respondido");

    @Getter
    private String status;
}

