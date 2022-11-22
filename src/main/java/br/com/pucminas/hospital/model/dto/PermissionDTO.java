package br.com.pucminas.hospital.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {

    private Long idPermission;
    private String description;
}