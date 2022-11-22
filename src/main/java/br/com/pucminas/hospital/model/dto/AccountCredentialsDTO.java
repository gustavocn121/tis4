package br.com.pucminas.hospital.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccountCredentialsDTO {

    @NonNull
    @NotEmpty(message = "Usuário não pode ser nulo")
    private String username;

    @NonNull
    @NotEmpty(message = "Senha não pode ser nula")
    private String password;
}
