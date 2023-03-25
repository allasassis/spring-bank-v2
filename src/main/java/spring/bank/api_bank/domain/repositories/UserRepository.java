package spring.bank.api_bank.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import spring.bank.api_bank.domain.models.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);
}
