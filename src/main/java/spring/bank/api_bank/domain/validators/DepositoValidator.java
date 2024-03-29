package spring.bank.api_bank.domain.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.bank.api_bank.domain.dto.DadosOperacao;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.models.Operacao;
import spring.bank.api_bank.domain.repositories.ClienteRepository;
import spring.bank.api_bank.infra.exception.ValidacaoException;

@Component
public class DepositoValidator implements Validator {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void validar(DadosOperacao dados, Cliente cliente) {
        if (dados.operacao() == Operacao.DEPOSITO) {
            if (dados.quantidade() <= 1000.0) {
                cliente.deposita(dados.quantidade());
                repository.save(cliente);
            } else {
                throw new ValidacaoException("Você só pode depositar R$ 1000.00 por vez!");
            }
        }
    }
}
