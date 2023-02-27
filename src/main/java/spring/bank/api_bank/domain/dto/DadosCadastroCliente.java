package spring.bank.api_bank.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCliente(@NotBlank String nome, @NotBlank String cpf, @NotBlank @Email String email, @NotBlank String telefone,
                                   @NotNull @Valid DadosCadastroEndereco endereco) {
}
