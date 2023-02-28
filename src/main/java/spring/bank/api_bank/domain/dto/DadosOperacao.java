package spring.bank.api_bank.domain.dto;

import jakarta.validation.constraints.NotNull;
import spring.bank.api_bank.domain.models.Operacao;

import java.time.LocalDateTime;

public record DadosOperacao(@NotNull Long id, @NotNull Operacao operacao, @NotNull Double quantidade, Long idRecebidor) {
    // Recebidor não é obrigatório, porque nem sempre é uma transferência

}
