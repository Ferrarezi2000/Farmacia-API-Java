package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FarmaciaDTO implements Serializable {

    private Long id;
    private String nome;
    private String localidade;
    private Boolean ativo;

    private String administradorSenha;
    private String administradorNome;

}
