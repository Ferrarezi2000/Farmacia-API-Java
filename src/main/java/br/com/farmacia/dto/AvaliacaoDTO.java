package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AvaliacaoDTO implements Serializable {

    private Long id;
//    private String usuarioSobrenome;
    private String facebookId;
    private String usuarioNome;
    private Long farmaciaId;
    private Long patrocinadorId;
    private Integer valor;
    private String comentario;
    private String resposta;
    private String usuarioImagem;
    private String emailFacebook;
    private Date momento;

}
