package com.example.DigitalBankAPI.controller;


import com.example.DigitalBankAPI.model.NovaProposta;
import com.example.DigitalBankAPI.model.Pessoa;
import com.example.DigitalBankAPI.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/proposta")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;


    @PostMapping
    public ResponseEntity savePessoa(@RequestBody @Valid NovaProposta novaProposta, UriComponentsBuilder uriComponentsBuilder){

        final Pessoa pessoa = pessoaService.create(novaProposta);

        final URI uri = uriComponentsBuilder.path("/api/proposta/{id}/etapa2").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

//    @PutMapping
//    public Pessoa updatePessoa(@RequestBody Pessoa pessoa){
//        return pessoaService.update(pessoa);
//    }

    @GetMapping(path = "/{id}")
    public Pessoa findPessoa(@PathVariable long id){
        Optional<Pessoa> pessoa = pessoaService.findById(id);
        return pessoa.get();
    }

//    @DeleteMapping
//    public void deletePessoa(@RequestBody Pessoa pessoa){
//        pessoaService.delete(pessoa);
//    }
}
