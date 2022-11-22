package br.com.pucminas.hospital.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderEnum {

    M("MASCULINO"),
    F("FEMININO");

    private String value;
}
