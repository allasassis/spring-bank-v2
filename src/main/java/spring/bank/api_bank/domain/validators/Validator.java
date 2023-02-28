package spring.bank.api_bank.domain.validators;

import spring.bank.api_bank.domain.dto.DadosOperacao;
import spring.bank.api_bank.domain.models.Cliente;

public interface Validator {
    void validar(DadosOperacao dados, Cliente cliente);
}
