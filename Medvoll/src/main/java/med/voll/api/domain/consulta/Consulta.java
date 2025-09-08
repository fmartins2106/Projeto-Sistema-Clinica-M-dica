package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Entity(name = "Consulta")
@Table(name = "consultas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id  // Indica que este campo é a chave primária da tabela (Primary Key).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Diz que o banco vai gerar o valor automaticamente (auto incremento)
    // quando um novo registro for inserido.
    private Long id;

    // Define um relacionamento Muitos-para-Um (muitas consultas podem ter um mesmo médico).
    // O 'fetch = LAZY' significa que o médico só será carregado do banco
    // quando for acessado explicitamente (economiza performance).
    @ManyToOne(fetch = FetchType.LAZY)
    // Coluna no banco que será a chave estrangeira (FK) para o medico
    @JoinColumn(name = "idmedico")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    // Coluna no banco que será a chave estrangeira (FK) para o paciente.
    @JoinColumn(name = "idpaciente")
    private Paciente paciente;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public void cancelar(MotivoCancelamento motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }























}
