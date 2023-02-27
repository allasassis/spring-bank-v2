package spring.bank.api_bank.domain.dto;

import spring.bank.api_bank.domain.models.Endereco;

public record DadosAtualizarCliente(String nome, String email, String telefone, Endereco endereco) {
}
