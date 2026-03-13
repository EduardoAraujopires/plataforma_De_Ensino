package oi.github.api.aplataformaEnsino.service;

import oi.github.api.aplataformaEnsino.model.Aluno;
import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository repository;

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Aluno findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Aluno save(Aluno aluno) {
        return repository.save(aluno);
    }

    public Aluno atualizarPorId(Aluno aluno, UUID id) {
        var alunoIndentificado = repository.findById(id).orElse(null);
        assert alunoIndentificado != null;
        return repository.save(alunoIndentificado);
    }

    public void deletarById(UUID id) {
        repository.deleteById(id);
    }

    public Aluno listaDeCursosAtravesDoId(UUID id) {
        var cursoIdentificado = repository.findById(id).orElse(null);
        Aluno aluno = new Aluno();
        aluno.curso.forEach(System.out::println);
        return aluno;
    }
}
