package oi.github.api.aplataformaEnsino.controller;

import oi.github.api.aplataformaEnsino.model.Aluno;
import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable UUID id){
        return alunoService.findById(id);
    }

    @PostMapping
    public Aluno saveAluno(@RequestBody Aluno aluno){
        return alunoService.save(aluno);
    }

    @PostMapping
    public Aluno updateAluno(@RequestBody Aluno aluno, @PathVariable UUID id){
      return alunoService.atualizarPorId(aluno, id);
    }

     @DeleteMapping("/{id}")
     public void deleteById(@PathVariable UUID id){
        alunoService.deletarById(id);
     }

     @GetMapping("/{id}/cursos")
     public void findByMatriculas(@PathVariable UUID id){
         alunoService.exibirDetalhesMatriculas(id);
     }

    @GetMapping("/{id}/cursos")
    public ResponseEntity<List<Curso>> listarCursosDoAluno(@PathVariable UUID id) {
        List<Curso> cursos = alunoService.listarCursosDoAluno(id);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}/matriculas")
    public ResponseEntity<Void> exibirDetalhesMatriculas(@PathVariable UUID id) {
        alunoService.exibirDetalhesMatriculas(id);
        return ResponseEntity.ok().build();
    }
}
