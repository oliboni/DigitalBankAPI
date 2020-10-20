package com.example.DigitalBankAPI.controller;

import com.example.DigitalBankAPI.exceptions.BadRequestException;
import com.example.DigitalBankAPI.exceptions.IllegalRequestException;
import com.example.DigitalBankAPI.exceptions.NotFoundRequestException;
import com.example.DigitalBankAPI.model.Documento;
import com.example.DigitalBankAPI.model.NovaPropostaDocumento;
import com.example.DigitalBankAPI.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/proposta/{id}/etapa3")
public class DocumentoController {
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
    DocumentoService documentoService;
    @Autowired
    BadRequestException badRequestException;
    @Autowired
    NotFoundRequestException notFoundRequestException;
    @Autowired
    IllegalRequestException illegalRequestException;

    @PostMapping
    @Transactional
    public ResponseEntity saveDocument(@RequestBody @Valid NovaPropostaDocumento novaPropostaDocumento
                                     , @PathVariable("id") Long id
                                     , UriComponentsBuilder uriComponentsBuilder){
        try {
            final Documento documento = documentoService.create(novaPropostaDocumento, id);
            if (documento != null) {
                final URI uri = uriComponentsBuilder.path("/api/proposta/{id}/").buildAndExpand(id).toUri();
                return ResponseEntity.created(uri).build();
            }
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e) {
            return illegalRequestException.illegalArgument(e);
        }catch (IllegalStateException e){
            return illegalRequestException.illegalState(e);
        }catch (RuntimeException e){
            return notFoundRequestException.notFound(e);
        }catch (Exception e) {
            return badRequestException.badRequest(e);
        }
    }
}
