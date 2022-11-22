package br.com.pucminas.hospital.model.entity;

import br.com.pucminas.hospital.model.enums.GenderEnum;
import br.com.pucminas.hospital.model.enums.SurgeryPatientEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "patient")
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(generator = "patient_generator")
    @SequenceGenerator(name = "patient_generator", sequenceName = "patient_sequence")
    private Long idPatient;

    @Column(name = "register", nullable = false, unique = true)
    private String register;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "gender", nullable = false, length = 80)
    private GenderEnum genderEnum;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "surgery_patient")
    private SurgeryPatientEnum surgeryPatientEnum;

    @Column(name = "surgery_date", nullable = false)
    private LocalDate surgeryDate;

    @Column(name = "is_nhsn_surgery", nullable = false)
    private Boolean isNhsnSurgery;

    @Column(name = "create_by")
    private String createdBy;

    @Column(name = "last_modified")
    private LocalDate lastModified = LocalDate.now();

}