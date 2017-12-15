package br.com.farmacia.dto;

import br.com.farmacia.model.Contato;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
    private String farmaciaTexto;
    private String farmaciaHoraAbrir;
    private String farmaciaHoraFechar;

    private String administradorToken;
    private String administradorSobrenome;

//    Patrocinador
    private Long patrocinadorId;
    private Long patrocinadorAdministradorId;
    private String patrocinadorNome;
    private Boolean patrocinadorAtivo;
    private BigDecimal patrocinadorValorMensal;
    private String patrocinadorTexto;
    private String patrocinadorHoraAbrir;
    private String patrocinadorHoraFechar;

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
    private List<Contato> contatos;

}
