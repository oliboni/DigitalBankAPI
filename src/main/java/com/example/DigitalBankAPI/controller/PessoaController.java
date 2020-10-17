package com.example.DigitalBankAPI.controller;


import com.example.DigitalBankAPI.model.Pessoa;
import com.example.DigitalBankAPI.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;


    @PostMapping
    public Pessoa savePessoa(@RequestBody Pessoa pessoa){
        return pessoaService.create(pessoa);
    }

    @PutMapping
    public Pessoa updatePessoa(@RequestBody Pessoa pessoa){
        return pessoaService.update(pessoa);
    }

    @GetMapping(path = "/{id}")
    public Pessoa findPessoa(@PathVariable long id){
        return pessoaService.findById(id);
    }

    @DeleteMapping
    public void deletePessoa(@RequestBody Pessoa pessoa){
        pessoaService.delete(pessoa);
    }
}
