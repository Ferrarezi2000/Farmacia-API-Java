package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class AdicionalDTO implements Serializable {

    private Long id;
    private Long farmaciaId;
    private Long patrocinadorId;
    private BigDecimal valor;
    private String item;
    private String subItem;

}
