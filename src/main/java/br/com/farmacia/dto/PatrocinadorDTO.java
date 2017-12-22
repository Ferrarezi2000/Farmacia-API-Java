package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class PatrocinadorDTO implements Serializable {

    private Long id;
    private Long administradorId;
    private String nome;
    private Boolean ativo;
    private BigDecimal valorMensal;
    private String senhaAcesso;
    private String usuarioAcesso;
    private Boolean adicionais;
    private String tituloAdicionais;
    private Boolean delivery;
    private String textoAdicionais;

    private String administradorToken;
    private String administradorSobrenome;

}
