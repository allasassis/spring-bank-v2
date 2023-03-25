package spring.bank.api_bank.domain.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.bank.api_bank.domain.dto.DadosOperacao;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.models.Operacao;
import spring.bank.api_bank.domain.repositories.ClienteRepository;
import spring.bank.api_bank.infra.exception.ValidacaoException;

@Component
public class TransferenciaValidator implements Validator{

    @Autowired
    private ClienteRepository repository;

    @Override
    public void validar(DadosOperacao dados, Cliente cliente) {
        if (dados.operacao() == Operacao.TRANSFERENCIA) {
            Cliente clienteTransferente = repository.getReferenceById(dados.id());
            Cliente clienteRecebente = repository.getReferenceById(dados.idRecebidor());

            if (clienteRecebente == null) {
                throw new ValidacaoException("Usuário recebente não existe OU não foi informado!");
            }

            clienteTransferente.sacar(dados.quantidade());
            clienteRecebente.deposita(dados.quantidade());
            repository.save(clienteTransferente);
            repository.save(clienteRecebente);
        }
    }
}
