package com.example.DigitalBankAPI.repository;

import com.example.DigitalBankAPI.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa,Long> {
//    Optional<Pessoa> findbyEmail(String email);
}
