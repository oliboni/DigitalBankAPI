package com.example.DigitalBankAPI.controller;


import com.example.DigitalBankAPI.exceptions.BadRequestException;
import com.example.DigitalBankAPI.exceptions.IllegalRequestException;
import com.example.DigitalBankAPI.model.NovaProposta;
import com.example.DigitalBankAPI.model.Pessoa;
import com.example.DigitalBankAPI.repository.PessoaRepository;
import com.example.DigitalBankAPI.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/proposta")
public class PessoaController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @Autowired
    PessoaService pessoaService;
    @Autowired
    PessoaRepository    pessoaRepository;
    @Autowired
    BadRequestException badRequestException;
    @Autowired
    IllegalRequestException illegalRequestException;

    @PostMapping
    public ResponseEntity<Object> savePessoa(@RequestBody @Valid NovaProposta novaProposta, UriComponentsBuilder uriComponentsBuilder){
        try {
            final Pessoa pessoa = pessoaService.create(novaProposta);

            final URI uri = uriComponentsBuilder.path("/api/proposta/{id}/etapa2").buildAndExpand(pessoa.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            return badRequestException.badRequest(e);
        }
    }

    @PutMapping
    public Pessoa updatePessoa(@RequestBody Pessoa pessoa){
        return pessoaService.update(pessoa);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> findPessoa(@PathVariable long id){
        Optional<Pessoa> pessoa = pessoaService.findById(id);
        if (pessoa.get().getEndereco() == null){
            String msg = "As informações do endereço ainda não foram enviadas!";
            return illegalRequestException.illegalState(msg);
        }
        if (pessoa.get().getDocumento() == null) {
            String msg = "O documento ainda não foi enviado!";
            return illegalRequestException.illegalState(msg);
        }
        return new ResponseEntity<>(pessoa.get(),HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public void deletePessoa(@RequestBody Pessoa pessoa){
        pessoaService.delete(pessoa);
    }
}
