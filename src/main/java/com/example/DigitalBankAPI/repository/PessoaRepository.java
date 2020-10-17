package com.example.DigitalBankAPI.repository;

import com.example.DigitalBankAPI.model.Pessoa;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
public interface PessoaRepository extends CrudRepository<Pessoa,Long> {

}
