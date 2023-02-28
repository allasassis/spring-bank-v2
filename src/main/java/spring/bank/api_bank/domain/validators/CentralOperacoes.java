package spring.bank.api_bank.domain.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.bank.api_bank.domain.dto.DadosDetalhamentoSaldo;
import spring.bank.api_bank.domain.dto.DadosOperacao;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.repositories.ClienteRepository;

import java.util.List;

@Service
public class CentralOperacoes {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private List<Validator> validadores;

    public DadosDetalhamentoSaldo validarOperacao(DadosOperacao dados) {
        Cliente cliente = clienteRepository.getReferenceById(dados.id());

        if (cliente == null) {
            throw new ValidacaoException("Usuário não existe!");
        }

        validadores.forEach(validator -> validator.validar(dados, cliente));
        return new DadosDetalhamentoSaldo(cliente);
    }
}
