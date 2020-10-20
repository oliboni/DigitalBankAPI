package com.example.DigitalBankAPI.repository;

import com.example.DigitalBankAPI.model.Documento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends CrudRepository<Documento, Long> {

}
