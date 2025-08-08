package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
        //implementando ResponseEntity. TEM QUE IMPLEMENTAR EM TODOS. RETORNO HTTP
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

//    @GetMapping
//    public Page<DadosListagemMedicos> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
//        return repository.findAll(paginacao).map(DadosListagemMedicos::new);
//    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDadosMedico(@RequestBody @Valid DadosAtualizacaoMedicos dadosAtualizacaoMedicos) {
        var medico = repository.getReferenceById(dadosAtualizacaoMedicos.id());
        medico.atualizarInformacoes(dadosAtualizacaoMedicos);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    // Metodo abaixo serve para poder excluir de forma total do banco de dados e do sistema.
//    @DeleteMapping("{id}")
//    @Transactional
//    public void excluirDadosMedico(@PathVariable Long id){
//        repository.deleteById(id);
//    }

}
