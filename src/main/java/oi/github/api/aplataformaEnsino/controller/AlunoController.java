package oi.github.api.aplataformaEnsino.controller;

import oi.github.api.aplataformaEnsino.model.Aluno;
import oi.github.api.aplataformaEnsino.service.AlunoService;
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
     public Aluno findByCurso(@PathVariable UUID id){
        return alunoService.listaDeCursosAtravesDoId(id);
     }
}
