package spring.bank.api_bank.controllers;

import com.electronwill.nightconfig.core.conversion.Path;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.bank.api_bank.domain.dto.DadosAutenticacao;
import spring.bank.api_bank.domain.models.Usuario;
import spring.bank.api_bank.domain.repositories.UserRepository;
import spring.bank.api_bank.domain.validators.CentralAutenticacao;
import spring.bank.api_bank.infra.security.DadosTokenJWT;
import spring.bank.api_bank.infra.security.TokenService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CentralAutenticacao centralAutenticacao;

    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication authentication = manager.authenticate(authenticationToken);

        String tokenGerado = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenGerado));
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity efetuarCadastro(@RequestBody @Valid DadosAutenticacao dados, UriComponentsBuilder uriB) {
        Usuario usuario = centralAutenticacao.validarECriptografar(dados);
        ResponseEntity responseEntity = efetuarLogin(dados);
        DadosTokenJWT tokenJWT = (DadosTokenJWT) responseEntity.getBody();

        URI uri =  uriB.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(tokenJWT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        usuario.ifPresent(usuario1 -> repository.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
