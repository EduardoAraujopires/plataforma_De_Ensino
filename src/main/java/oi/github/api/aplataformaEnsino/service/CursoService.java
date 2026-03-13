package oi.github.api.aplataformaEnsino.service;

import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findById(UUID id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateDadosCurso(Curso curso, UUID id) {
        var Curso = cursoRepository.findById(id).orElse(null);
        assert Curso != null;
        return cursoRepository.save(Curso);
    }

    public void deletarById(UUID id){
        cursoRepository.deleteById(id);
    }

    public Curso findByAluno(UUID id){
        Curso curso = new Curso();
        var idAluno = cursoRepository.findById(id).orElse(null);
        curso.alunos.forEach(System.out::println);
        return curso;
    }
}
