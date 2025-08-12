package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta){
        var dto = agendaDeConsultas.agendar(dadosAgendamentoConsulta);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta){
        agendaDeConsultas.cancelarConsulta(dadosCancelamentoConsulta);
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity cancelar(@PathVariable Long id) {
//        agendaDeConsultas.cancelarConsulta(new DadosCancelamentoConsulta(id, MotivoCancelamento.OUTROS)); // Motivo padrão ou outro
//        return ResponseEntity.noContent().build();
//    }


}
