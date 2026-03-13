package oi.github.api.aplataformaEnsino.service;

import oi.github.api.aplataformaEnsino.exception.VerificacaoId;
import oi.github.api.aplataformaEnsino.model.Aluno;
import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.model.NivelCurso;
import oi.github.api.aplataformaEnsino.repository.AlunoRepository;
import oi.github.api.aplataformaEnsino.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;


import static java.util.Arrays.stream;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;
    AlunoRepository alunoRepository;
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findById(UUID id) {
        return cursoRepository.findById(id).orElseThrow(VerificacaoId::new);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateDadosCurso(Curso curso, UUID id) {
        Curso cursoAtualizado = cursoRepository.findById(id).orElseThrow(VerificacaoId::new);
        cursoAtualizado.setNome(curso.getNome());
        cursoAtualizado.setAtivo(curso.getAtivo());
        cursoAtualizado.setDescricao(curso.getDescricao());
        cursoAtualizado.setCargaHoraria(curso.getCargaHoraria());
        cursoAtualizado.setDataCriacao(curso.getDataCriacao());
        cursoAtualizado.setAlunos(curso.getAlunos());
        cursoAtualizado.setNivel(curso.getNivel());
        return cursoRepository.save(cursoAtualizado);
    }

    public void deletarById(UUID id){
        cursoRepository.deleteById(id);
    }

    public Curso findByAluno(UUID id){
        Aluno idAluno = alunoRepository.findById(id).orElseThrow(VerificacaoId::new);
        Curso curso = cursoRepository.findById(idAluno.getId()).orElseThrow(VerificacaoId::new);
        curso.alunos.forEach(System.out::println);
        return curso;
    }

    public List<Curso> findByNivel(NivelCurso curso){
       return cursoRepository.findByNivel(curso);
    }
}
