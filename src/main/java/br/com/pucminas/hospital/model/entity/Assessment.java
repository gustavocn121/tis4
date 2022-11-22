package br.com.pucminas.hospital.model.entity;

import br.com.pucminas.hospital.model.enums.AssesmentCancelReasonEnum;
import br.com.pucminas.hospital.model.enums.AssesmentStatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "assessment")
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Assessment {

    @Id
    @GeneratedValue(generator = "assessment_generator")
    @SequenceGenerator(name = "assessment_generator", sequenceName = "assessment_sequence")
    private Long idAssessment;

    @Column(name = "assessment_number")
    private String assessmentNumberEnum;

    @Column(name = "call_day")
    private LocalDate callDay;

    @Column(name = "is_contact_done")
    private Boolean isContactDone;

    @Column(name = "symptoms_detail")
    private String symptomsDetail;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name="status")
    private AssesmentStatusEnum status;

    @Column(name="cancel_reason")
    private AssesmentCancelReasonEnum cancelReason;


}