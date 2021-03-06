package com.example.DigitalBankAPI.controller;


import com.example.DigitalBankAPI.exceptions.BadRequestException;
import com.example.DigitalBankAPI.exceptions.IllegalRequestException;
import com.example.DigitalBankAPI.exceptions.NotFoundRequestException;
import com.example.DigitalBankAPI.model.Endereco;
import com.example.DigitalBankAPI.model.NovaPropostaEndereco;
import com.example.DigitalBankAPI.service.EnderecoService;
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

@RestController
@RequestMapping(value = "/api/proposta/{id}/etapa2")
public class EnderecoController {

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
    EnderecoService enderecoService;
    @Autowired
    BadRequestException badRequestException;
    @Autowired
    NotFoundRequestException notFoundRequestException;
    @Autowired
    IllegalRequestException illegalRequestException;

    @PostMapping
    public ResponseEntity saveEndereco(@RequestBody @Valid NovaPropostaEndereco novaPropostaEndereco
                                     , @PathVariable("id") Long id
                                     , UriComponentsBuilder uriComponentsBuilder){
        try {
            final Endereco endereco = enderecoService.create(novaPropostaEndereco, id);

            if (endereco != null) {
                final URI uri = uriComponentsBuilder.path("/api/proposta/{id}/etapa3").buildAndExpand(id).toUri();
                return ResponseEntity.created(uri).build();
            }
             return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return illegalRequestException.illegalArgument(e);
        } catch (IllegalStateException e) {
            return illegalRequestException.illegalState(e);
        }catch (RuntimeException e){
            return notFoundRequestException.notFound(e);
        }catch (Exception e) {
            return badRequestException.badRequest(e);
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
