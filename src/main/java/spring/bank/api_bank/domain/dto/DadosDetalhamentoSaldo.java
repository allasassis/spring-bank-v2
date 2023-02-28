package spring.bank.api_bank.domain.dto;

import spring.bank.api_bank.domain.models.Cliente;

public record DadosDetalhamentoSaldo(Double saldo) {

        public DadosDetalhamentoSaldo(Cliente cliente) {
            this(cliente.getSaldo());
        }
}
