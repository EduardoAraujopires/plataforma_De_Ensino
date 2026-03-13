package oi.github.api.aplataformaEnsino.controller;

import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.model.NivelCurso;
import oi.github.api.aplataformaEnsino.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> findAll(){
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public Curso findByID(@PathVariable UUID id){
        return cursoService.findById(id);
    }

    @PostMapping
    public Curso saveCurso(@RequestBody Curso curso){
        return cursoService.save(curso);
    }

    @GetMapping("/{id}")
    public Curso findByAlunoPorId(@PathVariable UUID id){
        return cursoService.findByAluno(id);
    }

    @PutMapping("/{id}")
    public Curso updateCurso(@RequestBody Curso curso, @PathVariable UUID id){
        return cursoService.updateDadosCurso(curso, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        cursoService.deletarById(id);
    }

    @GetMapping
    public List<Curso> findByNivel(@RequestBody NivelCurso curso){
        return cursoService.findByNivel(curso);
    }
}
