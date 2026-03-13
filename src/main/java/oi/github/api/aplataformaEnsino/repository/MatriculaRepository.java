package oi.github.api.aplataformaEnsino.repository;

import oi.github.api.aplataformaEnsino.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {
    boolean existsByEmail(String email);
}


