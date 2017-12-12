package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class FarmaciaDTO implements Serializable {

    private Long id;
    private Long administradorId;
    private String nome;
    private String localidade;
    private Integer codigo;
    private Boolean ativo;
    private Boolean vip;
    private BigDecimal valorMensal;
    private String codigoImagem;

    private String administradorToken;
    private String administradorSobrenome;


}
