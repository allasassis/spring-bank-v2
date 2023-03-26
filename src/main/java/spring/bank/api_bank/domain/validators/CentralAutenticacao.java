package spring.bank.api_bank.domain.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.bank.api_bank.domain.dto.DadosAutenticacao;
import spring.bank.api_bank.domain.models.Usuario;
import spring.bank.api_bank.domain.repositories.UserRepository;
import spring.bank.api_bank.infra.exception.ValidacaoException;

@Service
public class CentralAutenticacao {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean validarECriptografar(DadosAutenticacao dadosAutenticacao) {
        UserDetails login = repository.findByLogin(dadosAutenticacao.login());
        if (login != null) {
            throw new ValidacaoException("Login já existe! Use outro nome de usuário, por favor!");
        }

        String senha = passwordEncoder.encode(dadosAutenticacao.senha());
        Usuario usuario = new Usuario(dadosAutenticacao.login(), senha);
        repository.save(usuario);
        return true;
    }
}
