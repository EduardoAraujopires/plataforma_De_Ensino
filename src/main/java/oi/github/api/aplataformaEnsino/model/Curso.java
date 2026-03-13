package oi.github.api.aplataformaEnsino.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso", nullable = false, unique = true)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao_curso")
    private String descricao;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @Column(name = "nivel_do_curso")
    private NivelCurso nivel;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "status_curso")
    private boolean ativo;

    @OneToMany(mappedBy = "tb_curso")
    public List<Aluno> alunos;
}
