package br.com.pucminas.hospital.model.dto;

import br.com.pucminas.hospital.model.enums.GenderEnum;
import br.com.pucminas.hospital.model.enums.SurgeryPatientEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PatientDTO {

    @JsonIgnore
    private Long idPatient;

    @NotEmpty(message = "Registro não pode ser nulo")
    private String register;

    @NotEmpty(message = "Nome não pode ser nulo")
    private String name;

    @NotEmpty(message = "Sexo não pode ser nulo")
    private GenderEnum genderEnum;

    @NotEmpty(message = "Númenro de telefone não pode ser nulo")
    private String phoneNumber;

    @NotEmpty(message = "Tipo de cirurgia não pode ser nulo")
    private SurgeryPatientEnum surgeryPatientEnum;

    @NotEmpty(message = "Data da cirurgia não pode ser nula")
    private LocalDate surgeryDate;

    @NotEmpty(message = "NHSN não pode ser nula")
    private Boolean isNhsnSurgery;

    private String createdBy;

    private LocalDate lastModified = LocalDate.now();
}