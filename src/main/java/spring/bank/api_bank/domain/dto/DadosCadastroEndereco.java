package spring.bank.api_bank.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(@NotBlank String logradouro, @NotBlank String bairro, @NotBlank String numero, String complemento,
                                    @NotBlank String cidade, @NotBlank @Pattern(regexp = "\\d{8}") String cep, @NotBlank String uf) {
}
