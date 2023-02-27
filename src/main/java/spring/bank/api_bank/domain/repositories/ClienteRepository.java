package spring.bank.api_bank.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bank.api_bank.domain.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
