package oi.github.api.aplataformaEnsino.exception;

public class VerificacaoAluno extends RuntimeException{

    public VerificacaoAluno() {
        super("Aluno não autorizado a fazer a matricula");
    }
}
