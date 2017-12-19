package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FormaPagamentoDTO implements Serializable {

    private Long id;
    private String tipo;
    private Long farmaciaId;
    private Long patrocinadorId;

}
