package br.com.pucminas.hospital.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUser {
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

    @NonNull
    @NotEmpty(message = "Você precisa informar se o usuário é um administrador")
    private Boolean isAdmin;
}
