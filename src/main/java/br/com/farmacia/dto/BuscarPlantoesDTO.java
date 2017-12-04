package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BuscarPlantoesDTO implements Serializable {

    private Integer dia;
    private String mes;

}
