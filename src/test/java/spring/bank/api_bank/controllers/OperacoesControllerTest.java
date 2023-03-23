package spring.bank.api_bank.controllers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import spring.bank.api_bank.domain.dto.DadosCadastroCliente;
import spring.bank.api_bank.domain.dto.DadosCadastroEndereco;
import spring.bank.api_bank.domain.dto.DadosOperacao;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.models.Operacao;
import spring.bank.api_bank.domain.validators.CentralOperacoes;
import spring.bank.api_bank.domain.validators.ValidacaoException;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class OperacoesControllerTest {

    @Autowired
    private CentralOperacoes central;

    @Test
    @DisplayName("Não pode sacar depois do horário limite do banco")
    void naoPodeSacarDepoisDoHorarioLimite() {
        Cliente cliente = new Cliente(new DadosCadastroCliente("Joao", "2312312321", "asda@gmail.com", "943281321",
                new DadosCadastroEndereco("sadsa", "sadsa", "sadsa", "sadsa", "sadsa", "32183219", "SP")));

        cliente.deposita(10000.0);
        DadosOperacao dadosOperacao = new DadosOperacao(1L, Operacao.SAQUE, 1000.0, null);

        try {
            central.validarOperacao(dadosOperacao);
        } catch (ValidacaoException e) {
            String expectedMessage = "Horário limite para saque e transferência atingido";
            Assertions.assertThat(e.getMessage().contains(expectedMessage));
        }
    }
}