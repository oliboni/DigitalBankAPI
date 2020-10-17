package com.example.DigitalBankAPI.service;

import com.example.DigitalBankAPI.model.Endereco;
import com.example.DigitalBankAPI.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    //consulta
    public Endereco findById(long id){
        final Optional<Endereco> enderecoOpcional = enderecoRepository.findById(id);
        if (enderecoOpcional.isPresent()){
            return enderecoOpcional.get();
        } else {
            throw new RuntimeException(String.format("Endereço id {%s} não encontrada no banco de dados", id));
        }
    }

    //insert
    public Endereco create(@RequestBody Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
    //update
    public Endereco update(@RequestBody Endereco endereco){
        return enderecoRepository.save(endereco);
    }
    //delete
    public void delete(@RequestBody Endereco endereco){enderecoRepository.delete(endereco);}
}
