package spring.bank.api_bank.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.bank.api_bank.domain.dto.DadosCadastroCliente;
import spring.bank.api_bank.domain.dto.DadosDetalhamentoCliente;
import spring.bank.api_bank.domain.dto.DadosListagemCliente;
import spring.bank.api_bank.domain.models.Cliente;
import spring.bank.api_bank.domain.repositories.ClienteRepository;

import java.net.URI;
import java.util.List;

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

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> cadastroCliente(@RequestBody DadosCadastroCliente dados, UriComponentsBuilder uriB) {
        Cliente cliente = new Cliente(dados);
        repository.save(cliente);

        URI uri = uriB.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }
}
