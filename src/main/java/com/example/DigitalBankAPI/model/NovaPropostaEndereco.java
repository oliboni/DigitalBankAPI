package com.example.DigitalBankAPI.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NovaPropostaEndereco {
    @NotBlank(message = "CEP inválido")
    @Pattern(regexp = "\\d{5}\\-\\d{3}", message = "CEP deve ser no formato 00000-000")
    @Size(min = 8, max = 9, message = "CEP inválido")
    private String cep;
    @NotBlank
    private String rua;
    @NotBlank
    private String bairro;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;

    protected NovaPropostaEndereco(){}

    public NovaPropostaEndereco(@NotBlank String cep, @NotBlank String rua, @NotBlank String bairro, @NotBlank String complemento, @NotBlank String cidade, @NotBlank String estado) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Endereco toModel(){
        return new Endereco(cep, rua, bairro, complemento, cidade, estado);
    }
}
