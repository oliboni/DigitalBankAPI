package com.example.DigitalBankAPI.service;

import com.example.DigitalBankAPI.model.Documento;
import com.example.DigitalBankAPI.model.NovaPropostaDocumento;
import com.example.DigitalBankAPI.model.Pessoa;
import com.example.DigitalBankAPI.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private PessoaService pessoaService ;

    public DocumentoService(DocumentoRepository documentoRepository){
        this.documentoRepository = documentoRepository;
    }

    //consulta
    public Documento findById(long id){
        final Optional<Documento> documentoOptional = documentoRepository.findById(id);
        if (documentoOptional.isPresent()){
            return documentoOptional.get();
        } else {
            throw new RuntimeException(String.format("Documento id {%s} não encontrado no banco de dados", id));
        }
    }

    //insert
    public Documento create(@RequestBody @Valid NovaPropostaDocumento novaPropostaDocumento, Long id) {

        Documento documento = novaPropostaDocumento.toModel();
        Optional<Pessoa> optionalPessoa = pessoaService.findById(id);
        if (optionalPessoa.isPresent()){
            Pessoa pessoa = optionalPessoa.get();
            pessoa.adicionaDocumento(documento);
            pessoaService.update(pessoa);
            return documento;
        }

        return null;
    }
//    //update
    public Documento update(@RequestBody Documento documento){
        return documentoRepository.save(documento);
    }
//    //delete
    public void delete(@RequestBody Documento documento){documentoRepository.delete(documento);}
}
