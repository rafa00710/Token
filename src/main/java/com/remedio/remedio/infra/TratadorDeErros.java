package com.remedio.remedio.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

//Essa anotação permite que quando acontecer algum erro ela seja inicializada.
@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratador404() {
        return ResponseEntity.notFound().build();
    }

    //Esse metodo vai tratar qualquer erro de requisição. Ex: Nome em branco, conforme o Dto.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratador400(MethodArgumentNotValidException ex) {
        List<DadosErros> erros = ex.getFieldErrors().stream()
                .map(DadosErros::new)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    public record DadosErros(String campo, String mensagem){

        public DadosErros(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
