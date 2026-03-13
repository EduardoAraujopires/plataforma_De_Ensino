package oi.github.api.aplataformaEnsino.exception;

public class VerificacaoEmail extends RuntimeException{

    public VerificacaoEmail() {
        super("Email não existe");
    }
}
