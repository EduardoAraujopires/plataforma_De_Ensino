package oi.github.api.aplataformaEnsino.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_aluno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno", nullable = false, unique = true)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefone", length = 10)
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "tb_aluno")
    public List<Curso> curso;
}
