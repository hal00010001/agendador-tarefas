package br.com.ambidextrous.agendadortarefas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        e.printStackTrace();

        return ResponseEntity.status(500)
                .body(e.getClass().getName() + " - " + e.getMessage());
    }

}
