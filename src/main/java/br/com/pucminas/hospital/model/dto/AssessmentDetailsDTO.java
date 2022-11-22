package br.com.pucminas.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentDetailsDTO {

    private String symptomsDetail;
    private Boolean isContactDone;

}