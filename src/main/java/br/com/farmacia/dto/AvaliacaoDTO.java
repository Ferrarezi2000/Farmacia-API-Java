package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AvaliacaoDTO implements Serializable {

    private Long id;
    private String usuarioSobrenome;
    private String usuarioNome;
    private Long farmaciaId;
    private Long patrocinadorId;
    private Integer valor;

}
