package br.com.pucminas.hospital.mapper;

import br.com.pucminas.hospital.model.dto.PatientDTO;
import br.com.pucminas.hospital.model.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDTO entityToDto(Patient entity);

    Patient dtoToEntity(PatientDTO dto);
}
