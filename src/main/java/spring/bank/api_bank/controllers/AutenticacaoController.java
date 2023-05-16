package spring.bank.api_bank.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import spring.bank.api_bank.domain.dto.DadosAutenticacao;
import spring.bank.api_bank.domain.models.Usuario;
import spring.bank.api_bank.domain.service.UserService;
import spring.bank.api_bank.infra.security.DadosTokenJWT;
import spring.bank.api_bank.infra.security.TokenService;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT>  efetuarLogin(@RequestBody DadosAutenticacao dados) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication authentication = manager.authenticate(authenticationToken);

        String tokenGerado = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenGerado));
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<DadosTokenJWT> efetuarCadastro(@RequestBody DadosAutenticacao dados) {
        DadosTokenJWT token = userService.cadastrar(dados);
        return ResponseEntity.status(201).body(token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuario(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
