package br.com.farmacia.builder;

import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.model.Administrador;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdministradorBuild {

    public Administrador build(Administrador administrador, AdministradorDTO dto) {
        if (dto.getId() == null) {
            administrador.setAtivo(true);
        }

        administrador.setNome(dto.getNome());
        administrador.setSobrenome(dto.getSobrenome());
        administrador.setSenha(dto.getSenha());

        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        System.out.println(token.substring(0,20));

        administrador.setToken(token);

        return administrador;
    }
}
