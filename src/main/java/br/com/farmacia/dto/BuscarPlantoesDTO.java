package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BuscarPlantoesDTO implements Serializable {

    private Integer dia;
    private String mes;
    private Integer ano;
    private Date data;

}
