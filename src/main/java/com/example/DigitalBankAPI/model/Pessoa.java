package com.example.DigitalBankAPI.model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private Date dataNascimento;
    private String cpf;
    @ManyToOne
    private Endereco endereco;

    protected Pessoa() {}

    public Pessoa(long id, String nome, String sobrenome, String email, Date dataNascimento, String cpf, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
