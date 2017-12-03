package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PatrocinadorDTO implements Serializable {

    private Long id;
    private String nome;
    private Boolean ativo;

}
