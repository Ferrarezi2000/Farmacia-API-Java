package br.com.farmacia.config;

import br.com.farmacia.model.Administrador;
import br.com.farmacia.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class Security {

    @Autowired private AdministradorRepository administradorRepository;

    public void check(String senha, String nome) {

        Administrador administrador = administradorRepository.findTopBySenhaAndNome(senha, nome);
        Assert.notNull(administrador, "Acesso não autorizado!");

    }
}
