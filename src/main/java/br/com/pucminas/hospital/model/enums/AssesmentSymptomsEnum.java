package br.com.pucminas.hospital.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum AssesmentSymptomsEnum {

    SINTOMA1("Nega sinais e sintomas - FO seca"),
    SINTOMA2("alteração urinaria nao sugestiva de lesao de raiz medlar"),
    SINTOMA3("Indicação de desbridamento"),
    SINTOMA4("Queixa de dor em FO, limpa e seca"),
    SINTOMA5("Nega sinais e sintomas - FO seca"),
    SINTOMA6("INFECÇÃO PROFUNDA"),
    SINTOMA7("Não atendeu as 03 tentativas"),
    SINTOMA8("PACIENTE FALECEU"),
    SINTOMA9("FO COM HIPEREMIA EM BORDAS, PONTOS DE ESFACELO E SECREÇÃO SEROSA.");

    private String description;

    @Override
    public String toString() {
        return this.getDescription();
    }
}
