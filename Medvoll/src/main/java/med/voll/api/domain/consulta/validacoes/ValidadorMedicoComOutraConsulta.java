package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorMedicoComOutraConsulta {
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        var medicoPossuiOutraConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndData(dadosAgendamentoConsulta.idMedico(),dadosAgendamentoConsulta.data());
        if (medicoPossuiOutraConsultaMesmoHorario){
            throw new ValidacaoException("Médico já possui outra consulta agendada neste mesmo horário.");
        }
    }
}
