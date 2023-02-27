package spring.bank.api_bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.bank.api_bank.domain.dto.DadosDetalhamentoClientes;
import spring.bank.api_bank.domain.dto.DadosListagemCliente;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.repositories.ClienteRepository;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public ResponseEntity<List<DadosListagemCliente>> listarClientes() {
        List<DadosListagemCliente> clientes = repository.findAll().stream().map(DadosListagemCliente::new).toList();
        return ResponseEntity.ok(clientes);
    }
}
