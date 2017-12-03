package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PreencherCalendarioDTO implements Serializable {

    private Integer dias;
    private String mes;

    private String administradorToken;
    private String administradorSobrenome;

}
