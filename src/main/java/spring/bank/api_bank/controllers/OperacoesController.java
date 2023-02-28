package spring.bank.api_bank.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.bank.api_bank.domain.dto.DadosDetalhamentoSaldo;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.repositories.ClienteRepository;

@RestController
@RequestMapping("operacoes")
public class OperacoesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoSaldo> mostrarSaldo(@PathVariable Long id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoSaldo(cliente));
    }

    @PutMapping("/depositar/{id}/{quantidade}")
    public ResponseEntity<DadosDetalhamentoSaldo> depositar(@PathVariable Double quantidade, @PathVariable Long id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.deposita(quantidade);
        clienteRepository.save(cliente);
        return ResponseEntity.ok(new DadosDetalhamentoSaldo(cliente));
    }

    @PutMapping("/sacar/{id}/{quantidade}")
    public ResponseEntity<DadosDetalhamentoSaldo> sacar(@PathVariable Double quantidade, @PathVariable Long id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.sacar(quantidade);
        clienteRepository.save(cliente);
        return ResponseEntity.ok(new DadosDetalhamentoSaldo(cliente));
    }

    @PutMapping("/transferir/{transferente}/{recebedor}/{quantidade}")
    public ResponseEntity<DadosDetalhamentoSaldo> transferir(@PathVariable Double quantidade, @PathVariable Long transferente, @PathVariable Long recebedor) {
        Cliente clienteTransferente = clienteRepository.getReferenceById(transferente);
        Cliente clienteRecebedor = clienteRepository.getReferenceById(recebedor);
        clienteTransferente.sacar(quantidade);
        clienteRecebedor.deposita(quantidade);
        clienteRepository.save(clienteTransferente);
        clienteRepository.save(clienteRecebedor);
        return ResponseEntity.ok(new DadosDetalhamentoSaldo(clienteTransferente));
    }
}
