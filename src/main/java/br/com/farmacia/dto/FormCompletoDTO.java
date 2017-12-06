package br.com.farmacia.dto;

import br.com.farmacia.anotacao.Boleano;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class FormCompletoDTO implements Serializable {

//    Farmácia
    private Long administradorId;
    private String nome;
    private String localidade;
    private Boolean ativo;
    private Boolean vip;
    private BigDecimal valorMensal;
    private Boolean farmaciaAtivo;

    private String administradorToken;
    private String administradorSobrenome;

//    Endereço
    private String logradouro;
    private String bairro;
    private String numero;
    private Long farmaciaId;
    private Long patrocinadorId;
    private Boolean enderecoAtivo;

//    Contato
    private String tipo;
    private String texto;
    private Boolean contatoAtivo;

}
