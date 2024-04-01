package com.remedio.remedio.remedio;


import com.remedio.remedio.dto.AtualizarRemedioDto;
import com.remedio.remedio.dto.CadastroRemedioDto;
import com.remedio.remedio.ViaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//Vai gerar uma tabela com o nome:
@Table (name = "remedio")
@Entity(name = "remedios")
//Lombok:
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Remedio {

    //Construtor feito a partir do Dto para salvar as informações no post do controller
    public Remedio(CadastroRemedioDto dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.sintomas = dados.sintomas();
        this.quantidade = dados.quantidade();
        this.validade = dados.validade();
        this.via = dados.via();
    }

    //Vai criar o id automatico como chave primaria
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sintomas;
    private int quantidade;
    private LocalDate validade;
    private boolean ativo;

    //Enum mapeado pelo jpa
    @Enumerated(EnumType.STRING)
    private ViaEnum via;

    public void atualizarInformações(AtualizarRemedioDto dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.via() != null){
            this.via = dados.via();
        }
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }
}
