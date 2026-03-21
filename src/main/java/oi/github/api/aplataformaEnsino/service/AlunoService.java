package oi.github.api.aplataformaEnsino.service;

import oi.github.api.aplataformaEnsino.exception.VerificacaoId;
import oi.github.api.aplataformaEnsino.model.Aluno;
import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.model.Matricula;
import oi.github.api.aplataformaEnsino.model.StatusDaMatricula;
import oi.github.api.aplataformaEnsino.repository.AlunoRepository;
import oi.github.api.aplataformaEnsino.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository repository;

    @Autowired
    MatriculaRepository matriculaRepository;

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

    public void exibirDetalhesMatriculas(UUID alunoId) {
        Aluno aluno = repository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        System.out.println("Matrículas do aluno: " + aluno.getNome());
        System.out.println("=================================");

        aluno.getMatricula().forEach(matricula -> {
            System.out.println("Curso: " + matricula.getCurso().getNome());
            System.out.println("Status: " + matricula.getStatusDaMatricula());
            System.out.println("Data: " + matricula.getDataMatricula());
            if (matricula.getNotaFinal() != null) {
                System.out.println("Nota Final: " + matricula.getNotaFinal());
            }
            System.out.println("---------------------------------");
        });
    }

    public List<Curso> listarCursosDoAluno(UUID alunoId) {
        // Busca o aluno pelo ID
        Aluno aluno = repository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + alunoId));

        // Busca todas as matrículas do aluno
        List<Matricula> matriculas = matriculaRepository.findByAlunoId(alunoId);

        // Extrai os cursos das matrículas
        List<Curso> cursos = matriculas.stream()
                .map(Matricula::getCurso)
                .collect(Collectors.toList());

        // Imprime os nomes dos cursos (alternativa ao seu forEach)
        cursos.forEach(curso -> System.out.println(curso.getNome()));

        return cursos;
    }

    // Método alternativo: lista os cursos ativos do aluno
    public List<Curso> listarCursosAtivosDoAluno(UUID alunoId) {
        Aluno aluno = repository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        // Filtra apenas matrículas com status ATIVO
        return aluno.getMatricula().stream()
                .filter(matricula -> matricula.getStatusDaMatricula() == StatusDaMatricula.ATIVO)
                .map(Matricula::getCurso)
                .collect(Collectors.toList());
    }
}
