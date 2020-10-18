package com.example.DigitalBankAPI.controller;


import com.example.DigitalBankAPI.model.Endereco;
import com.example.DigitalBankAPI.model.NovaPropostaParte2;
import com.example.DigitalBankAPI.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/proposta/{id}/etapa2")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity saveEndereco(@RequestBody NovaPropostaParte2 novaPropostaParte2
                                     , @PathVariable("id") Long id
                                     , UriComponentsBuilder uriComponentsBuilder){
        System.out.println("Passou");

            final Endereco endereco = enderecoService.create(novaPropostaParte2, id);
        try {
            if (endereco != null) {
                final URI uri = uriComponentsBuilder.path("/api/proposta/{id}/etapa3").buildAndExpand(id).toUri();
                return ResponseEntity.created(uri).build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping
//    public Endereco updateEndereco(@RequestBody Endereco endereco){
//        return enderecoService.update(endereco);
//    }

    @GetMapping(path = "/{id}")
    public Endereco findEndereco(@PathVariable long id){
        return enderecoService.findById(id);
    }

//    @DeleteMapping
//    public void deleteEndereco(@RequestBody Endereco endereco){
//        enderecoService.delete(endereco);
//    }
}
