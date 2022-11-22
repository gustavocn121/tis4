package br.com.pucminas.hospital.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SurgeryPatientEnum {

    FX("FRATURA"),
    MA("ARTRODESE DE COLUNA VERTEBRAL"),
    HPRO("ARTROPLASTIA DE QUADRIL"),
    KPRO("ARTROPLASTIA DE JOELHO"),
    VIDEO("CIRURGIAS REALIZADAS ATRAVES DE VIDEOARTROSCOPIA"),
    ACV("ACV");

    @Getter
    private String value;
}
