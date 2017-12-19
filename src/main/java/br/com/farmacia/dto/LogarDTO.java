package br.com.farmacia.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LogarDTO implements Serializable {

    private String senhaAcesso;
    private String usuarioAcesso;

}
