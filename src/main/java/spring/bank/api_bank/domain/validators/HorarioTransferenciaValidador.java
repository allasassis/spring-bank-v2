package spring.bank.api_bank.domain.validators;

import org.springframework.stereotype.Component;
import spring.bank.api_bank.domain.dto.DadosOperacao;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.models.Operacao;

import java.time.LocalDateTime;

@Component
public class HorarioTransferenciaValidador implements Validator{

    @Override
    public void validar(DadosOperacao dados, Cliente cliente) {
        if (dados.operacao() == Operacao.SAQUE || dados.operacao() == Operacao.TRANSFERENCIA) {
            LocalDateTime now = LocalDateTime.now();

            Boolean antesDaAbertura = now.getHour() < 5;
            Boolean depoisDoEncerramento = now.getHour() > 21;

            if (antesDaAbertura || depoisDoEncerramento) {
                throw new ValidacaoException("Horário limite para saque e transferência atingido (Medida para prevenção de roubos)." +
                        " Horário permitido: 05:00 até as 21:00");
            }
        }
    }
}

