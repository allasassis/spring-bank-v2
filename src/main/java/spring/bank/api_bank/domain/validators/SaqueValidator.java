package spring.bank.api_bank.domain.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.bank.api_bank.domain.dto.DadosOperacao;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.models.Operacao;
import spring.bank.api_bank.domain.repositories.ClienteRepository;

@Component
public class SaqueValidator implements Validator{

    @Autowired
    private ClienteRepository repository;

    @Override
    public void validar(DadosOperacao dados, Cliente cliente) {
        if (dados.operacao() == Operacao.SAQUE) {
            cliente.sacar(dados.quantidade());
            repository.save(cliente);
        }
    }
}
