package br.com.pucminas.hospital.mapper;

import br.com.pucminas.hospital.model.dto.AssessmentDTO;
import br.com.pucminas.hospital.model.entity.Assessment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssessmentMapper {

    AssessmentMapper INSTANCE = Mappers.getMapper(AssessmentMapper.class);


    @Mapping(source = "patient.register", target = "patientRegister")
    AssessmentDTO entityToDto(Assessment entity);

    List<AssessmentDTO> entityToDto(List<Assessment> entity);

    Assessment dtoToEntity(AssessmentDTO dto);
    List<Assessment> dtoToEntity(List<AssessmentDTO> dto);
}
