package com.remedio.remedio.dto;

import com.remedio.remedio.ViaEnum;
import com.remedio.remedio.remedio.Remedio;

import java.time.LocalDate;

public record DetalhamentoRemedioDto(Long id,

                                     String nome,

                                     ViaEnum via,

                                     int quantidade,

                                     LocalDate validade,

                                     boolean ativo) {
    //Construtor do nosso retorno na url do controller do get
    public DetalhamentoRemedioDto(Remedio remedio) {
        this(remedio.getId(),
                remedio.getNome(),
                remedio.getVia(),
                remedio.getQuantidade(),
                remedio.getValidade(),
                remedio.isAtivo());
    }
}
