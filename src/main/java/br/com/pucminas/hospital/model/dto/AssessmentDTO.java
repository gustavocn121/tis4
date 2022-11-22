package br.com.pucminas.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentDTO {

    private Long idAssessment;

    private LocalDate callDay;

    @NotEmpty(message = "Contato realizado não pode ser nulo")
    private Boolean isContactDone;

    @NotEmpty(message = "Detalhes do sintomas não podem ser nulos")
    private String symptomsDetail;

    @NotEmpty(message = "Registro do paciente não pode ser nulo")
    private String patientRegister;

}