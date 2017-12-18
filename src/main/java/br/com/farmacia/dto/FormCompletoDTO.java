package br.com.farmacia.dto;

import br.com.farmacia.model.Avaliacao;
import br.com.farmacia.model.Contato;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
    private Integer farmaciaAvaliacao;
    private Integer farmaciaAcesso;
    private String administradorToken;
    private String administradorSobrenome;
    private List<Avaliacao> farmaciaAvaliacoes;
    private Double farmaciaMediaAvaliacao;
    private Integer farmaciaTotalAvaliacoes;
    private Integer farmaciaTotalAvaliacoes5;
    private Integer farmaciaTotalAvaliacoes4;
    private Integer farmaciaTotalAvaliacoes3;
    private Integer farmaciaTotalAvaliacoes2;
    private Integer farmaciaTotalAvaliacoes1;
    private Date farmaciaAvaliacaoMomento;

//    Patrocinador
    private Long patrocinadorId;
    private Long patrocinadorAdministradorId;
    private String patrocinadorNome;
    private Boolean patrocinadorAtivo;
    private BigDecimal patrocinadorValorMensal;
    private String patrocinadorTexto;
    private String patrocinadorHoraAbrir;
    private String patrocinadorHoraFechar;
    private Integer patrocinadorAvaliacao;
    private Integer patrocinadorAcesso;
    private List<Avaliacao> patrocinadorAvaliacoes;
    private Double patrocinadorMediaAvaliacao;

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
