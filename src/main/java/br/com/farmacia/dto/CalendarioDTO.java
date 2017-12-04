package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CalendarioDTO implements Serializable {

    private Long id;
    private Integer dia;
    private String mes;
    private Integer ano;

    private String administradorToken;
    private String administradorSobrenome;

}
