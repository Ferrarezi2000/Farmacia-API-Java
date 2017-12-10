package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ContatoDTO implements Serializable {

    private Long id;
    private String tipo;
    private String texto;
    private Long farmaciaId;
    private Long patrocinadorId;
    private Boolean ativo;

    private String administradorToken;
    private String administradorSobrenome;

}
