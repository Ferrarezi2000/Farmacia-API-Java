package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EnderecoDTO implements Serializable {

    private Long id;
    private String logradouro;
    private String bairro;
    private String numero;
    private Long farmaciaId;
    private Long patrocinadorId;

    private String administradorToken;
    private String administradorSobrenome;


}
