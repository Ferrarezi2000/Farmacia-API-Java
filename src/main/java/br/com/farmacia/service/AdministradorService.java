package br.com.farmacia.service;

import br.com.farmacia.model.Administrador;
import br.com.farmacia.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdministradorService {

    @Autowired private AdministradorRepository repository;

    public String cadastrarAdmInicial(Administrador administrador) {
        List<Administrador> administradores = repository.findAll();

        if (administradores.size() == 0) {
            administrador.setAtivo(true);
            administrador.setNome("Thiago");
            administrador.setSobrenome("Ferrarezi");
            administrador.setSenha("2000");
            UUID uuid = UUID.randomUUID();
            String token = uuid.toString();
            administrador.setToken(token);
            repository.save(administrador);

            return "Administrador cadastrado com sucesso...";
        } else {
            return "Falha... Administrador já cadastrado...";
        }
    }
}
