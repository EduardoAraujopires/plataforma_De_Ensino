package oi.github.api.aplataformaEnsino.controller;

import oi.github.api.aplataformaEnsino.model.Aluno;
import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.model.Matricula;
import oi.github.api.aplataformaEnsino.service.MatriculaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @GetMapping
    public  boolean buscarAluno(@RequestBody List<Matricula> buscandoAluno, @PathVariable UUID idAluno){
       return matriculaService.procurandoSeAlunoJaExiste(buscandoAluno, idAluno);
    }

    @PostMapping
    public Matricula realizarMatricula(@RequestBody Aluno aluno, @RequestBody Curso curso){
        return matriculaService.realizarMatricula(aluno, curso);
    }


    @GetMapping
    public List<Matricula> buscarPorStatus(@RequestBody List<Matricula> status){
        return matriculaService.findByStatus(status);
    }

    @GetMapping
    public List<Matricula> buscarTodasMatriculas(){
        return matriculaService.findAll();
    }

    @GetMapping
    public List<Matricula> buscarMatriculas(){
        return matriculaService.findAll();
    }

    @PutMapping("/{id}")
    public void cancelarMatricula(@PathVariable UUID id){
        matriculaService.cancelarMatricula(id);
    }

    @PutMapping("/{id}")
    public Matricula concluirMAtricula(@PathVariable Double nota, @RequestBody Matricula matricula){
        return matriculaService.concluir(nota, matricula);
    }

}