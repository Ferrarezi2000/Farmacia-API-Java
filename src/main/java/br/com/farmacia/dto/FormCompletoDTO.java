package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class FormCompletoDTO implements Serializable {

//    Farmácia
    private Long farmaciaId;
    private Long farmaciaAdministradorId;
    private String farmaciaNome;
    private String farmaciaLocalidade;
    private Boolean farmaciaAtivo;
    private Boolean farmaciaVip;
    private BigDecimal farmaciaValorMensal;

    private String administradorToken;
    private String administradorSobrenome;

//    Patrocinador
    private Long patrocinadorId;
    private Long patrocinadorAdministradorId;
    private String patrocinadorNome;
    private Boolean patrocinadorAtivo;
    private BigDecimal patrocinadorValorMensal;
//    Endereço

    private Long enderecoId;
    private String enderecoLogradouro;
    private String enderecoBairro;
    private String enderecoNumero;
    private Long enderecoFarmaciaId;
    private Long enderecoPatrocinadorId;
    private Boolean enderecoAtivo;

//    Contato
    private Long contatoId;
    private String contatoTipo;
    private String contatoTexto;
    private Boolean contatoAtivo;

}
