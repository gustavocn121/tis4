package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.mapper.AssessmentMapper;
import br.com.pucminas.hospital.model.dto.AssessmentDTO;
import br.com.pucminas.hospital.model.dto.AssessmentDetailsDTO;
import br.com.pucminas.hospital.model.entity.Assessment;
import br.com.pucminas.hospital.model.entity.Patient;
import br.com.pucminas.hospital.model.enums.AssesmentStatusEnum;
import br.com.pucminas.hospital.model.enums.AssessmentNumberEnum;
import br.com.pucminas.hospital.repository.AssessmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AssessmentService {

    @Autowired
    AssessmentRepository repository;

    private static final Integer SECOND_ASSESSMENT_TRY = 30;
    private static final Integer THIRD_ASSESSMENT_TRY = 60;

    public void createPatientAssessment(Patient patient) {

        List<Assessment> assessments = new ArrayList<>();

        Assessment firstAssessment = new Assessment();
        firstAssessment.setCallDay(LocalDate.now());
        firstAssessment.setPatient(patient);
        firstAssessment.setStatus(AssesmentStatusEnum.AGENDADO);
        firstAssessment.setAssessmentNumberEnum(AssessmentNumberEnum.PRIMEIRA.getValue());
        firstAssessment.setSymptomsDetail("");


        Assessment secondAssessment = new Assessment();
        secondAssessment.setCallDay(LocalDate.now().plusDays(SECOND_ASSESSMENT_TRY));
        secondAssessment.setPatient(patient);
        secondAssessment.setStatus(AssesmentStatusEnum.AGENDADO);

        firstAssessment.setAssessmentNumberEnum(AssessmentNumberEnum.SEGUNDA.getValue());
        secondAssessment.setSymptomsDetail("");

        Assessment thirdAssessment = new Assessment();
        thirdAssessment.setCallDay(LocalDate.now().plusDays(THIRD_ASSESSMENT_TRY));
        thirdAssessment.setPatient(patient);
        thirdAssessment.setStatus(AssesmentStatusEnum.AGENDADO);

        firstAssessment.setAssessmentNumberEnum(AssessmentNumberEnum.TERCEIRA.getValue());
        thirdAssessment.setSymptomsDetail("");

        assessments.add(firstAssessment);
        assessments.add(secondAssessment);
        assessments.add(thirdAssessment);

        repository.saveAll(assessments);
    }

    public List<Assessment> findDailyAssessment() {
        return repository.findAllDailyAssessment();
    }

    public List<AssessmentDTO> findAssessmentByRegister(String patientRegister) {
        var assessment = repository.findAssessmentByRegister(patientRegister).get();
        return AssessmentMapper.INSTANCE.entityToDto(assessment);
    }

    public AssessmentDTO updateAssessment(Long idAssesment, AssessmentDetailsDTO assessmentDetailsDTO) {

        var assessment = repository.findById(idAssesment)
                .orElseThrow(() -> new BusinesException("Nenhum paciente encontrado com esse id"));

        assessment.setIsContactDone(assessmentDetailsDTO.getIsContactDone());
        assessment.setSymptomsDetail(assessmentDetailsDTO.getSymptomsDetail());
        assessment.setStatus(AssesmentStatusEnum.RESPONDIDO);

        return AssessmentMapper.INSTANCE.entityToDto(repository.save(assessment));
    }
}