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
    @OneToOne(cascade = CascadeType.MERGE)
    private Documento documento;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void adicionaEndereco(Endereco novoEndereco){
        Assert.isNull(this.endereco, "Já existe um endereço cadastrado");
        this.endereco = novoEndereco;
    }

    public void adicionaDocumento(Documento documento) {
        Assert.state(this.endereco != null , "O endereço não pode ser nulo");
        Assert.isNull(this.documento, "Já existe um documento cadastrado");
        this.documento = documento;
    }

    public boolean cadastroCompleto(){
        return this.endereco != null && this.documento != null;
    }
}
