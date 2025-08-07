package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedicos> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
    }

//    @GetMapping
//    public Page<DadosListagemMedicos> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
//        return repository.findAll(paginacao).map(DadosListagemMedicos::new);
//    }

    @PutMapping
    @Transactional
    public void atualizarDadosMedico(@RequestBody @Valid DadosAtualizacaoMedicos dadosAtualizacaoMedicos){
        var medico = repository.getReferenceById(dadosAtualizacaoMedicos.id());
        medico.atualizarInformacoes(dadosAtualizacaoMedicos);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }





    // Metodo abaixo serve para poder excluir de forma total do banco de dados e do sistema.
//    @DeleteMapping("{id}")
//    @Transactional
//    public void excluirDadosMedico(@PathVariable Long id){
//        repository.deleteById(id);
//    }

}
