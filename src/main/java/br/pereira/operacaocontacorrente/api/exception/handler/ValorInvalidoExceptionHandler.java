package br.pereira.operacaocontacorrente.api.exception.handler;

import br.pereira.operacaocontacorrente.api.exception.SaqueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ValorInvalidoExceptionHandler {

    @ExceptionHandler(SaqueException.class)
    public ResponseEntity tratar (SaqueException e) {
        ApiError error = new ApiError(
                "saque_nao_efetivado",
                 e.getMessage(),
                LocalDateTime.now());

        return ResponseEntity.unprocessableEntity()
                .body(error);
    }
}