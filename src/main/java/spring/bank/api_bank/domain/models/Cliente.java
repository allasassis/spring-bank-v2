package spring.bank.api_bank.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.bank.api_bank.domain.dto.DadosAtualizarCliente;
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
    private Double saldo = 0.0;

    @Embedded
    private Endereco endereco;

    public Cliente(DadosCadastroCliente dados) {
        nome = dados.nome();
        cpf = dados.cpf();
        email = dados.email();
        telefone = dados.telefone();
        endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizarCliente dados) {
        if (dados.nome() != null) {
            nome = dados.nome();
        }
        if (dados.email() != null) {
            email = dados.email();
        }
        if (dados.telefone() != null) {
            telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            endereco.atualizarInformacoes(dados);
        }
    }

    public void deposita(Double quantidade) {
        saldo += quantidade;
    }

    public void sacar(Double quantidade) {

        // taxa de saque e transferência -> 5%
        Double taxa = quantidade * 0.05;
        Double total = quantidade + taxa;

        if (this.saldo >= total) {
            saldo -= total;
        } else {
            throw new RuntimeException("Saldo é insuficiente para fazer o saque OU transferência." +
                    " Operação cancelada! Lembrando que é cobrado uma taxa de 5% em saques e transferências");
        }
    }
}
