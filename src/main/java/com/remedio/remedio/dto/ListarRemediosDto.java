package com.remedio.remedio.dto;

import com.remedio.remedio.ViaEnum;
import com.remedio.remedio.remedio.Remedio;

import java.time.LocalDate;

public record ListarRemediosDto(Long id,
                                String nome,
                                int quantivade,
                                ViaEnum via,
                                LocalDate validade) {
    //Temos que criar esse metodo para retornar no get do controller
    public ListarRemediosDto(Remedio remedio){
        this(remedio.getId(), remedio.getNome(), remedio.getQuantidade(), remedio.getVia(), remedio.getValidade());
    }
}
