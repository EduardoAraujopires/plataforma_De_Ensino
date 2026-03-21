package oi.github.api.aplataformaEnsino.repository;

import oi.github.api.aplataformaEnsino.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    boolean existsByEmail(String email);
    Optional<Aluno> findByEmail(String email);
}
