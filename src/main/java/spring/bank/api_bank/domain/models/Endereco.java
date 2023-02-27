package spring.bank.api_bank.domain.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.bank.api_bank.domain.dto.DadosCadastroEndereco;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private String cidade;
    private String cep;
    private String uf;

    public Endereco(DadosCadastroEndereco dados) {
        logradouro = dados.logradouro();
        bairro = dados.bairro();
        numero = dados.numero();
        complemento = dados.complemento();
        cidade = dados.cidade();
        cep = dados.cep();
        uf = dados.uf();
    }
}
