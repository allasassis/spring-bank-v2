package spring.bank.api_bank.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.bank.api_bank.domain.dto.DadosAutenticacao;
import spring.bank.api_bank.domain.models.Usuario;
import spring.bank.api_bank.domain.repositories.UserRepository;
import spring.bank.api_bank.infra.exception.ValidacaoException;
import spring.bank.api_bank.infra.security.DadosTokenJWT;
import spring.bank.api_bank.infra.security.TokenService;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public DadosTokenJWT cadastrar(DadosAutenticacao dados) {
        Usuario usuario = validarECriptografar(dados);
        userRepository.save(usuario);
        return new DadosTokenJWT(tokenService.gerarToken(usuario));
    }

    public void deleteUser(Long id) {
        verifier(id);
        userRepository.deleteById(id);
    }

    public Usuario validarECriptografar(DadosAutenticacao dados) {

        if (userRepository.findByLogin(dados.login()) != null) {
            throw new ValidacaoException("Login já existe! Use outro nome de usuário, por favor!");
        }

        String senha = passwordEncoder.encode(dados.senha());
        Usuario usuario = new Usuario(dados.login(), senha);
        userRepository.save(usuario);
        return usuario;
    }

    private Usuario verifier(Long id) {
        Optional<Usuario> usuario = userRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new ValidacaoException("Esse ID não existe!");
        }

        return usuario.get();
    }


}
