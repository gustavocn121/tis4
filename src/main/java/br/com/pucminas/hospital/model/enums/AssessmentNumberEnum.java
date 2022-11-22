package br.com.pucminas.hospital.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AssessmentNumberEnum {

    PRIMEIRA("PRIMEIRA"),
    SEGUNDA("SEGUNDA"),
    TERCEIRA("TERCEIRA");

    @Getter
    private String value;
}
