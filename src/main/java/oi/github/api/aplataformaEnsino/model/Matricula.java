package oi.github.api.aplataformaEnsino.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula", nullable = false, unique = true)
    private UUID id;

    @Column(name = "data_matricula", nullable = false)
    private LocalDateTime dataMatricula;

    @Column(name = "nota_final")
    private Double notaFinal;

    @Column(name = "Status_Da_Matricula")
    private StatusDaMatricula statusDaMatricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno" )
    private Aluno aluno;
}
