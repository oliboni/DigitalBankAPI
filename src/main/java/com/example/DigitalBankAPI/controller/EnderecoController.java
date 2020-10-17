package com.example.DigitalBankAPI.controller;


import com.example.DigitalBankAPI.model.Endereco;
import com.example.DigitalBankAPI.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;


    @PostMapping
    public Endereco saveEndereco(@RequestBody Endereco endereco){
        return enderecoService.create(endereco);
    }

    @PutMapping
    public Endereco updateEndereco(@RequestBody Endereco endereco){
        return enderecoService.update(endereco);
    }

    @GetMapping(path = "/{id}")
    public Endereco findEndereco(@PathVariable long id){
        return enderecoService.findById(id);
    }

    @DeleteMapping
    public void deleteEndereco(@RequestBody Endereco endereco){
        enderecoService.delete(endereco);
    }
}
