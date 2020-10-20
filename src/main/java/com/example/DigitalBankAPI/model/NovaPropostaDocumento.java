package com.example.DigitalBankAPI.model;

import javax.validation.constraints.NotBlank;

public class NovaPropostaDocumento { ;
    @NotBlank
    private String docFrente;

    protected NovaPropostaDocumento(){

    }

    public NovaPropostaDocumento(@NotBlank String docFrente) {
        this.docFrente = docFrente;
    }

    public Documento toModel(){
        return new Documento(docFrente);
    }

    public String  getDocFrente() {
        return docFrente;
    }
}
