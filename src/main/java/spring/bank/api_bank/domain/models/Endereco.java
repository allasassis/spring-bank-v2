package spring.bank.api_bank.domain.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.bank.api_bank.domain.dto.DadosAtualizarCliente;
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

        public void atualizarInformacoes(DadosAtualizarCliente dados) {
        if (dados.endereco().logradouro != null) {
            logradouro = dados.endereco().logradouro;
        }
        if (dados.endereco().bairro != null) {
            bairro = dados.endereco().bairro;
        }
        if (dados.endereco().numero != null) {
            numero = dados.endereco().numero;
        }
        if (dados.endereco().cidade != null) {
            cidade = dados.endereco().cidade;
        }
        if (dados.endereco().uf != null) {
            uf = dados.endereco().uf;
        }
        if (dados.endereco().cep != null) {
            cep = dados.endereco().cep;
        }
        if (dados.endereco().complemento != null) {
            complemento = dados.endereco().complemento;
        }
    }
}
