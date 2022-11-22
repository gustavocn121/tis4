package br.com.pucminas.hospital.model.dto;

import br.com.pucminas.hospital.model.enums.AssessmentNumberEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParamsStatisticDTO {

    private AssessmentNumberEnum assessmentNumberEnum;
    private LocalDate initialDate;
    private LocalDate finalDate;
}