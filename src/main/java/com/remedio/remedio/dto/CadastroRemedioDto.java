package com.remedio.remedio.dto;

import com.remedio.remedio.ViaEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CadastroRemedioDto(
        //Anotação de validação e não permite String vazia ou com espaços.
        @NotBlank
        String nome,
        @NotBlank
        String sintomas,
        int quantidade,
        //Anotação permite inserir apenas datas futuras
        @Future
        LocalDate validade,
        @Enumerated
        ViaEnum via) {
}
