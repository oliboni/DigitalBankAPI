package com.example.DigitalBankAPI.model;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String sobrenome;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private LocalDate dataNascimento;
    @OneToOne(cascade = CascadeType.MERGE)
    private Endereco endereco;


    protected Pessoa() {
    }

    public Pessoa(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank @Email String email, @Past @NotNull LocalDate dataNascimento, @NotNull @CPF String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public long getId() {
        return id;
    }


    public void adicionaEndereco(Endereco novoEndereco){
        Assert.isNull(this.endereco, "Já existe um endereço cadastrado");
        this.endereco = novoEndereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
