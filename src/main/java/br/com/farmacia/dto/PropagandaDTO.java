package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PropagandaDTO implements Serializable {

    private Long id;
    private Long farmaciaId;
    private Long patrocinadorId;
    private String diaSemana;

    private String administradorToken;
    private String administradorSobrenome;

}
