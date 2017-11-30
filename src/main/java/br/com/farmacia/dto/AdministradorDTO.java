package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AdministradorDTO implements Serializable {

    private Long id;
    private String nome;
    private String sobrenome;
    private String senha;
    private Boolean ativo;

}
