package br.com.pucminas.hospital.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.pucminas.hospital.model.request.CreateUser;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    public static UserDTO getFromCreateUserData(CreateUser data) {
        UserDTO dto = new UserDTO();

        dto.setEmail(data.getEmail());
        dto.setUserName(data.getUserName());
        dto.setFullName(data.getFullName());
        dto.setPassword(data.getPassword());

        return dto;
    }

    private Long idUser;

    @NonNull
    @NotEmpty(message = "Usuário não pode ser nulo")
    private String userName;

    @NonNull
    @Email
    @NotEmpty(message = "Email não pode ser nulo")
    private String email;

    @NonNull
    @NotEmpty(message = "Nome não pode ser nulo")
    private String fullName;

    @NonNull
    @NotEmpty(message = "Senha não pode ser nula")
    private String password;

    private List<PermissionDTO> permission;
}