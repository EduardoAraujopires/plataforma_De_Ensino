package oi.github.api.aplataformaEnsino.service;

import oi.github.api.aplataformaEnsino.exception.VerificacaoId;
import oi.github.api.aplataformaEnsino.model.Aluno;
import oi.github.api.aplataformaEnsino.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.findById(id).orElseThrow(VerificacaoId::new);
    }

    public Aluno save(Aluno aluno) {
        return repository.save(aluno);
    }

    public Aluno atualizarPorId(Aluno aluno, UUID id) {
        var alunoIndentificado = repository.findById(id).orElseThrow(VerificacaoId::new);
        assert alunoIndentificado != null;
        alunoIndentificado.setNome(aluno.getNome());
        alunoIndentificado.setTelefone(aluno.getTelefone());
        alunoIndentificado.setEmail(aluno.getEmail());
        alunoIndentificado.setDataCadastro(aluno.getDataCadastro());
        return repository.save(alunoIndentificado);
    }

    public void deletarById(UUID id) {
        repository.deleteById(id);
    }

    public Aluno listaDeCursosAtravesDoId(UUID id) {
        Aluno cursoIdentificado = repository.findById(id).orElseThrow(VerificacaoId::new);
        cursoIdentificado.curso.forEach(System.out::println);
        return cursoIdentificado;
    }
}
