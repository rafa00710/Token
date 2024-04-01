package com.remedio.remedio.dto;

import com.remedio.remedio.ViaEnum;
import jakarta.validation.constraints.NotNull;

public record AtualizarRemedioDto(
        @NotNull
        Long id,
        String nome,
        ViaEnum via) {
}
