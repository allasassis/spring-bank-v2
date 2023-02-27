package spring.bank.api_bank.domain.dto;

import spring.bank.api_bank.domain.models.Cliente;

public record DadosDetalhamentoClientes(String nome, String cpf, String email, String telefone) {
    DadosDetalhamentoClientes(Cliente c) {
        this(c.getNome(), c.getCpf(), c.getEmail(), c.getTelefone());
    }
}
