package spring.bank.api_bank.domain.dto;

import spring.bank.api_bank.domain.models.Cliente;

public record DadosListagemCliente(String nome, String telefone, String email) {

    public DadosListagemCliente(Cliente c) {
        this(c.getNome(), c.getTelefone(), c.getEmail());
    }
}
