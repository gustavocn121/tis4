package br.com.pucminas.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private Integer answeredCalls;
    private Integer missedCalls;
    private Integer deaths;
    private Integer total;
    private Integer unsuccessfully;
    private Integer didtRespondThreeAttempts;
    private Integer nonExistentNumber;
    private Integer unavailableNumber;
    private Integer isntPatientsPhone;

}