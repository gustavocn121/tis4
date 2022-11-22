package br.com.pucminas.hospital.mapper;

import br.com.pucminas.hospital.model.dto.PermissionDTO;
import br.com.pucminas.hospital.model.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    PermissionDTO entityToDto(Permission entity);

    Permission dtoToEntity(PermissionDTO dto);
}
