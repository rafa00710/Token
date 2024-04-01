package com.remedio.remedio.controller;

import com.remedio.remedio.dto.DadosAutenticacaoDto;
import com.remedio.remedio.dto.TokenDto;
import com.remedio.remedio.service.AutentificacaoService;
import com.remedio.remedio.service.TokenService;
import com.remedio.remedio.usuarios.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    //Instanciando uma classe, já existente na biblioteca.
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacaoDto dados){

        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        var autenticacao = manager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new TokenDto(tokenJWT));

    }
}
