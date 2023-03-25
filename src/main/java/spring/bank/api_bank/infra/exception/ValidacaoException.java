package spring.bank.api_bank.infra.exception;

import java.io.Serial;

public class ValidacaoException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -1803635021102400072L;

    public ValidacaoException(String msg) {
        super((msg));
    }
}
