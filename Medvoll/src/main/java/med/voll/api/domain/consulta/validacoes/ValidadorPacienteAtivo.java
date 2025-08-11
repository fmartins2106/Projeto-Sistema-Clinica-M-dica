package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteAtivo {
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        var pacienteAtivo = pacienteRepository.findAtivoById(dadosAgendamentoConsulta.idPaciente());
        if (!pacienteAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo.");
        }
    }

}
