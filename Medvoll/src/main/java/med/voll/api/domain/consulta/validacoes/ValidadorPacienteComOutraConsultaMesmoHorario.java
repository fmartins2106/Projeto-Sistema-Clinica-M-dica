package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorPacienteComOutraConsultaMesmoHorario implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var existeConsulta = consultaRepository
                .existsByPacienteIdAndData(dados.idPaciente(), dados.data());

        if (existeConsulta) {
            throw new ValidacaoException("Paciente já possui consulta nesse mesmo horário.");
        }
    }
}
