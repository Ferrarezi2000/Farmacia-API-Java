package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlantaoDTO implements Serializable {

    private Long id;
    private Long farmaciaId;
    private String data;
}
