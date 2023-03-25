package spring.bank.api_bank.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.bank.api_bank.domain.dto.DadosDetalhamentoSaldo;
import spring.bank.api_bank.domain.dto.DadosOperacao;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.repositories.ClienteRepository;
import spring.bank.api_bank.domain.validators.CentralOperacoes;

@RestController
@RequestMapping("operacoes")
@SecurityRequirement(name = "bearer-key")
public class OperacoesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CentralOperacoes central;

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoSaldo> mostrarSaldo(@PathVariable Long id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoSaldo(cliente));
    }

    @PutMapping("/depositar")
    public ResponseEntity<DadosDetalhamentoSaldo> depositar(@RequestBody DadosOperacao dados) {
        DadosDetalhamentoSaldo dadosDetalhamentoSaldo = central.validarOperacao(dados);
        return ResponseEntity.ok(dadosDetalhamentoSaldo);
    }

    @PutMapping("/sacar")
    public ResponseEntity<DadosDetalhamentoSaldo> sacar(@RequestBody DadosOperacao dados) {
        DadosDetalhamentoSaldo dadosDetalhamentoSaldo = central.validarOperacao(dados);
        return ResponseEntity.ok(dadosDetalhamentoSaldo);
    }

    @PutMapping("/transferir")
    public ResponseEntity<DadosDetalhamentoSaldo> transferir(@RequestBody DadosOperacao dados) {
        DadosDetalhamentoSaldo dadosDetalhamentoSaldo = central.validarOperacao(dados);
        return ResponseEntity.ok(dadosDetalhamentoSaldo);
    }
}
