package com.example.DigitalBankAPI.model;

import com.example.DigitalBankAPI.service.validators.BeforeDate;
import com.example.DigitalBankAPI.service.validators.UniqueValue;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class NovaProposta {
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotNull
    @CPF
    @UniqueValue(domainClass = Pessoa.class, fieldName = "cpf")
    private String cpf;
    @NotBlank
    @Email
    @UniqueValue(domainClass = Pessoa.class, fieldName = "email")
    private String email;
    @Past
    @NotNull
    @BeforeDate(years = 18)
    private LocalDate dataNascimento;

    public NovaProposta(@NotBlank String nome, @NotBlank String sobrenome, @NotNull @CPF String cpf, @NotBlank @Email String email, @Past @NotNull LocalDate dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Pessoa toModel(){
        return new Pessoa(nome,sobrenome,email,dataNascimento,cpf);
    }
}
