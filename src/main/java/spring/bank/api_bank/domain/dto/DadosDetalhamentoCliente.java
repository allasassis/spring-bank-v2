package spring.bank.api_bank.domain.dto;

import jakarta.validation.Valid;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.models.Endereco;

public record DadosDetalhamentoCliente(String nome, String cpf, String email, String telefone, Endereco endereco) {
    public DadosDetalhamentoCliente(Cliente c) {
        this(c.getNome(), c.getCpf(), c.getEmail(), c.getTelefone(), c.getEndereco());
    }
}
