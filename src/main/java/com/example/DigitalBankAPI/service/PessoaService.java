package com.example.DigitalBankAPI.service;

import com.example.DigitalBankAPI.model.Pessoa;
import com.example.DigitalBankAPI.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

        public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

//    public boolean findEmail(String email){
//        final Optional<Pessoa> pessoaOptional = pessoaRepository.findbyEmail(email);
//
//        if(pessoaOptional.isPresent()){
//            return true; //Existe
//        } else {
//            return false; //Não existe
//        }
//    }

    //consulta
    public Pessoa findById(long id){
        final Optional<Pessoa> pessoaOpcional = pessoaRepository.findById(id);
        if (pessoaOpcional.isPresent()){
            return pessoaOpcional.get();
        } else {
            throw new RuntimeException(String.format("Pessoa id {%s} não encontrada no banco de dados", id));
        }
    }

    //insert
    public Pessoa create(@RequestBody Pessoa pessoa) {
//        final boolean validaEmail = this.findEmail(pessoa.getEmail());
//
//        if (!validaEmail) {
//            return pessoaRepository.save(pessoa);
//        } else {
//            throw new RuntimeException(String.format("Email já cadastrado!"));
//        }
        return pessoaRepository.save(pessoa);
    }
    //update
    public Pessoa update(@RequestBody Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
    //delete
    public void delete(@RequestBody Pessoa pessoa){pessoaRepository.deleteById(pessoa.getId());}
}
