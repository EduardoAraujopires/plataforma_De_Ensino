package oi.github.api.aplataformaEnsino.repository;

import oi.github.api.aplataformaEnsino.model.Curso;
import oi.github.api.aplataformaEnsino.model.NivelCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CursoRepository extends JpaRepository<Curso, UUID> {
    List<Curso> findByNivel(NivelCurso curso);

}
