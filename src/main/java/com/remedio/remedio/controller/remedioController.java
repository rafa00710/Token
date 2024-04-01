package com.remedio.remedio.controller;

import com.remedio.remedio.dto.AtualizarRemedioDto;
import com.remedio.remedio.dto.CadastroRemedioDto;
import com.remedio.remedio.dto.DetalhamentoRemedioDto;
import com.remedio.remedio.dto.ListarRemediosDto;
import com.remedio.remedio.remedio.Remedio;
import com.remedio.remedio.repository.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/remedios")
public class remedioController {


    @Autowired //Gestão de dependencia, chamando o jpa:
    private RemedioRepository remedioRepository;

    @PostMapping
    //Essa anotação Transactional, permite que nenhum dado seja perdido
    @Transactional
    //Anotação requesty, significa o que vou passar no corpo da requisição e as validações.
    public ResponseEntity<DetalhamentoRemedioDto> cadastrar(@RequestBody @Valid CadastroRemedioDto dados, UriComponentsBuilder uriBuilder) {
        var remedio = new Remedio(dados);

        remedioRepository.save(remedio);
        //Vai criar um url do id  e retornar
        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoRemedioDto(remedio));
    }

    @GetMapping
    public ResponseEntity<List<ListarRemediosDto>> listar() {
        //Retornado uma lista do Jpa
        var lista =  remedioRepository.findAllByAtivoTrue().stream().map(ListarRemediosDto::new).toList();

        return ResponseEntity.ok(lista);

    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoRemedioDto> atualizar(@RequestBody @Valid AtualizarRemedioDto dados) {
        var remedio = remedioRepository.getReferenceById(dados.id());
        remedio.atualizarInformações(dados);

        return ResponseEntity.ok(new DetalhamentoRemedioDto(remedio));

    }

    @DeleteMapping("/{id}")
    @Transactional
    //Essa anotação Path, sabe que vamos pasar qualquer id na url.
    public ResponseEntity<Void> excluir(@PathVariable long id) {
        remedioRepository.deleteById(id);

        //ResponseEntity para informar o retorno na url: 204 no content
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    //ResponseEntity para informar o retorno na url: 204 no content
    public ResponseEntity<Void> inativar(@PathVariable long id) {
        var remedio = remedioRepository.getReferenceById(id);
        remedio.inativar();

        return ResponseEntity.noContent().build();

    }

    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        var remedio = remedioRepository.getReferenceById(id);
        remedio.ativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    //Essa anotação Path, sabe que vamos passar qualquer id na url.
    public ResponseEntity<DetalhamentoRemedioDto> detalhar(@PathVariable long id) {
        var remedio = remedioRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoRemedioDto(remedio));
    }


}
