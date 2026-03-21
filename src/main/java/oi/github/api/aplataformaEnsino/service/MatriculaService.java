package oi.github.api.aplataformaEnsino.service;

import oi.github.api.aplataformaEnsino.exception.VerificacaoAluno;
import oi.github.api.aplataformaEnsino.exception.VerificacaoEmail;
import oi.github.api.aplataformaEnsino.exception.VerificacaoId;
import oi.github.api.aplataformaEnsino.model.Aluno;
import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.model.Matricula;
import oi.github.api.aplataformaEnsino.model.StatusDaMatricula;
import oi.github.api.aplataformaEnsino.repository.AlunoRepository;
import oi.github.api.aplataformaEnsino.repository.CursoRepository;
import oi.github.api.aplataformaEnsino.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MatriculaService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public boolean procurandoSeAlunoJaExiste(List<Matricula> alunosEncontrados, UUID idAluno) {
        var verificandoSeExiste = alunosEncontrados.stream().anyMatch(m -> m.getAluno().getId().equals(idAluno));
        if ((!verificandoSeExiste)) {
            throw new VerificacaoAluno();
        }
        return true;
    }

    public Matricula realizarMatricula(Aluno aluno, Curso curso) {
        Matricula matricula = new Matricula();
        long idadeAluno = ChronoUnit.YEARS.between(aluno.getDataNascimento(), LocalDate.now());
        List<Matricula> verificaoSeJaExisteAluno = new ArrayList<>();
        Aluno idAluno = repository.findById(aluno.getId()).orElseThrow(VerificacaoId::new);
        Curso idCurso = cursoRepository.findById(curso.getId()).orElseThrow(VerificacaoId::new);
        assert idAluno != null;
        if (procurandoSeAlunoJaExiste(verificaoSeJaExisteAluno, idAluno.getId())) {
            matricula.setCurso(idCurso);
        }
        if (idadeAluno < 16) {
            throw new VerificacaoAluno();
        }
        if (repository.existsByEmail(aluno.getEmail())) {
            throw new VerificacaoEmail();
        }
        if (curso.getAtivo().equals(true)) {
            matricula.setAluno(idAluno);
            matricula.setCurso(idCurso);
            matriculaRepository.save(matricula);
        }
        return null;
    }

    public List<Matricula> findByStatus(List<Matricula> list) {
        List<StatusDaMatricula> listaStatus = List.of(StatusDaMatricula.ATIVO, StatusDaMatricula.CANCELADO, StatusDaMatricula.CONCLUIDO);

        return list.stream().filter(m -> listaStatus.contains(m.getStatusDaMatricula())).toList();
        // listando por status utilizando o filter
    }

    public void cancelarMatricula(UUID id) {
        Matricula matricula = new Matricula();
        Matricula dadosMatricula = matriculaRepository.findById(id).orElseThrow(VerificacaoId::new);
        if (dadosMatricula.equals(matricula)) {
            matricula.setStatusDaMatricula(StatusDaMatricula.CANCELADO);
        }
    }

    public Matricula concluir(Double notaDoAluno, Matricula matricula) {
        Matricula matricula1 = matriculaRepository.findById(matricula.getId()).orElseThrow(VerificacaoId::new);
        if (matricula1 != null) {
            notaDoAluno(notaDoAluno);
            return matriculaRepository.save(matricula1);
        }
        return null;
    }

    public List<Matricula> findAll() {
        return matriculaRepository.findAll();
    }
    public void notaDoAluno(Double notaFinal) {
        if (notaFinal >= 0 && notaFinal <= 10) {
            if (notaFinal >= 6) {
                System.out.println("Status Aluno: Passou de Ano, nota: " + notaFinal);
            }
            if (notaFinal == 5) {
                System.out.println("Status Aluno: Recuperação, nota: " + notaFinal);
            } else {
                System.out.println("Status Aluno: Reprovado, nota: " + notaFinal);
            }
        }
    }
}
