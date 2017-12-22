package br.com.farmacia.dto;

import br.com.farmacia.model.Adicional;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class FarmaciaDTO implements Serializable {

    private Long id;
    private Long administradorId;
    private String nome;
    private String localidade;
    private String texto;
    private Integer codigo;
    private Boolean ativo;
    private Boolean vip;
    private BigDecimal valorMensal;
    private String codigoImagem;
    private Boolean adicionais;
    private String tituloAdicionais;
    private String senhaAcesso;
    private String usuarioAcesso;
    private Boolean delivery;
    private String textoAdicionais;
    private List<Contato> contatos;
    private List<Adicional> listaAdicionais;
    private Endereco endereco;

    private String administradorToken;
    private String administradorSobrenome;


}
