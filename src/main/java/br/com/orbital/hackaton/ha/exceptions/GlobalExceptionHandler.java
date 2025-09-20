package br.com.orbital.hackaton.ha.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Captura exceções quando uma entidade não é encontrada (ex: buscar cliente por um ID que não existe).
     * Retorna HTTP 404 (Not Found).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Captura os erros de validação dos DTOs (acionado pelo @Valid).
     * Retorna HTTP 400 (Bad Request) com uma lista dos campos e seus erros.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Captura qualquer outra exceção não prevista.
     * É o nosso "porto seguro" para evitar que a aplicação quebre sem dar resposta.
     * Retorna HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        // Na versão final, é uma boa prática não expor o stack trace para o cliente,
        // mas sim logá-lo em um sistema de monitoramento (ex: ex.printStackTrace();).
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Classe auxiliar para padronizar o corpo do erro
    public record ApiError(HttpStatus status, String message) {}
}