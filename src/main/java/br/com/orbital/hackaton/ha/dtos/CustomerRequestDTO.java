package br.com.orbital.hackaton.ha.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequestDTO (
        @NotBlank(message = "O nome completo é obrigatório.")
        @Size(max = 255, message = "O nome completo deve ter no máximo 255 caracteres.")
        String fullName,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O formato do e-mail é inválido.")
        @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres.")
        String email,

        @NotBlank(message = "O telefone é obrigatório.")
        String phone
){
}
