package spring.bank.api_bank.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.bank.api_bank.domain.dto.DadosCadastroCliente;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Cliente(DadosCadastroCliente dados) {
        nome = dados.nome();
        cpf = dados.cpf();
        email = dados.email();
        telefone = dados.telefone();
        endereco = new Endereco(dados.endereco());
    }
}
