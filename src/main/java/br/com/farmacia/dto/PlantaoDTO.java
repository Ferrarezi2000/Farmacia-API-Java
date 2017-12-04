package br.com.farmacia.dto;

import br.com.farmacia.model.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlantaoDTO implements Serializable {

    private Long id;
    private Long farmaciaId;
    private Long calendarioId;
    private Endereco endereco;

    private String administradorToken;
    private String administradorSobrenome;
}
