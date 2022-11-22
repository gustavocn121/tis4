package br.com.pucminas.hospital.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
    public enum AssesmentCancelReasonEnum {

    OBITO("Obito"),
    TELEFONE_INDISPONIVEL("telefone_indisponivel"), // Nao cadastrado
    TELEFONE_INEXISTE("telefone_inexistente"), // esse numero de telefoned nao existe
    TELEFONE_NAO_PERTENCE("Telefone_nao_pertence"); // nao pertence

    private String value;
}
