package com.example.DigitalBankAPI.service;

import com.example.DigitalBankAPI.model.NovaProposta;
import com.example.DigitalBankAPI.model.Pessoa;
import com.example.DigitalBankAPI.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

        public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

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
    public Pessoa create(@RequestBody @Valid NovaProposta novaProposta, UriComponentsBuilder uriComponentsBuilder) {
        final Pessoa pessoa = novaProposta.toModel();
        return pessoaRepository.save(pessoa);
    }
    //update
//    public Pessoa update(@RequestBody Pessoa pessoa){
//        return pessoaRepository.save(pessoa);
//    }
    //delete
//    public void delete(@RequestBody Pessoa pessoa){pessoaRepository.deleteById(pessoa.getId());}
}
