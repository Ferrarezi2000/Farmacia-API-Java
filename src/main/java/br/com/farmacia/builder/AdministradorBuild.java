package br.com.farmacia.builder;

import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.model.Administrador;
import org.springframework.stereotype.Component;

@Component
public class AdministradorBuild {

    public Administrador build(Administrador administrador, AdministradorDTO dto) {
        administrador.setAtivo(dto.getAtivo());
        administrador.setNome(dto.getNome());
        administrador.setSobrenome(dto.getSobrenome());
        administrador.setSenha(dto.getSenha());

        return administrador;
    }
}
