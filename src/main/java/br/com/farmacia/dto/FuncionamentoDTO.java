package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FuncionamentoDTO implements Serializable {

    private Long id;
    private String inicio;
    private String fim;
    private String diaSemama;
    private Long farmaciaId;
    private Long patrocinadorId;

}
