package com.example.DigitalBankAPI.service;

import com.example.DigitalBankAPI.model.Endereco;
import com.example.DigitalBankAPI.model.NovaPropostaEndereco;
import com.example.DigitalBankAPI.model.Pessoa;
import com.example.DigitalBankAPI.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private PessoaService pessoaService ;
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
    public Endereco create(@RequestBody @Valid NovaPropostaEndereco novaPropostaEndereco, Long id) {
        Endereco endereco = novaPropostaEndereco.toModel();
        Optional<Pessoa> optionalPessoa = pessoaService.findById(id);
        if (optionalPessoa.isPresent()){
            Pessoa pessoa = optionalPessoa.get();
            pessoa.adicionaEndereco(endereco);
            pessoaService.update(pessoa);
            return endereco;
        }

        return null;
    }
//    //update
    public Endereco update(@RequestBody Endereco endereco){
        return enderecoRepository.save(endereco);
    }
//    //delete
    public void delete(@RequestBody Endereco endereco){enderecoRepository.delete(endereco);}
}
