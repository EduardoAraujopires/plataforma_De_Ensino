package oi.github.api.aplataformaEnsino.repository;

import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.model.Matricula;
import oi.github.api.aplataformaEnsino.model.StatusDaMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {

    List<Matricula> findByAlunoId(UUID id);
    List<Matricula> findByAlunoIdAndStatus(UUID id, StatusDaMatricula matricula);

        boolean existsByAlunoIdAndCursoIdAndStatus(UUID idAluno, Curso curso, StatusDaMatricula matricula);
}


