package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PlantaoDTO implements Serializable {

    private Long id;
    private Long farmaciaId;
    private Date data;

    private String administradorToken;
    private String administradorSobrenome;
}
