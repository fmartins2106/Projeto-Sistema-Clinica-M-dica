package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository; // Injeta o repositório para acessar o banco

    @PostMapping //“Esse metodo vai responder a requisições HTTP POST nesse endpoint”.
    @Transactional // Garante transação no banco
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente, // Recebe JSON validado
            UriComponentsBuilder uriComponentsBuilder) {

        var paciente = new Paciente(dadosCadastroPaciente); // Converte DTO em entidade
        pacienteRepository.save(paciente); // Salva no banco

        var uri = uriComponentsBuilder.path("/pacientes/{id}") // Monta URI do novo paciente
                .buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri) // Retorna 201 Created com Location
                .body(new DadosDetalhamentoPaciente(paciente)); // Corpo com dados do paciente
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listagemPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = pacienteRepository.findByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDadosPaciente(@RequestBody @Valid DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {
        var paciente = pacienteRepository.getReferenceById(dadosAtualizacaoPaciente.id());
        paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

}
