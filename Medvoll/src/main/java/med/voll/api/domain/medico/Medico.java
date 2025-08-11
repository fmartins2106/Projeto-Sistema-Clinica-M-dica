package med.voll.api.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter // Gera automaticamente os métodos getters para todos os atributos da classe (ex: getId(), getNome(), etc.)
@NoArgsConstructor // Gera automaticamente um construtor **sem argumentos** (construtor padrão)
@AllArgsConstructor // Gera automaticamente um construtor **com todos os atributos** como argumentos
@EqualsAndHashCode(of = "id") // Gera automaticamente os métodos equals() e hashCode() com base apenas no campo "id"
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    @NotNull
    private boolean ativo = true;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoMedicos dadosAtualizacaoMedicos) {
        if (dadosAtualizacaoMedicos.nome() != null){
            this.nome = dadosAtualizacaoMedicos.nome();
        }
        if (dadosAtualizacaoMedicos.telefone() != null){
            this.telefone = dadosAtualizacaoMedicos.telefone();
        }
        if (dadosAtualizacaoMedicos.endereco() != null){
            this.endereco.atualizarEndereco(dadosAtualizacaoMedicos.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
