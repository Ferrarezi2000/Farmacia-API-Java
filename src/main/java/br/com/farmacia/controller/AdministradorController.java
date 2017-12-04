package br.com.farmacia.controller;

import br.com.farmacia.builder.AdministradorBuild;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.model.Administrador;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.AdministradorRepository;
import br.com.farmacia.service.AdministradorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/administrador")
@Api(description = "Administrador")
public class AdministradorController extends AbstractRestController{

    @Autowired private AdministradorRepository repository;
    @Autowired private AdministradorService service;
    @Autowired private AdministradorBuild build;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Administrador>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @PostMapping("/logar")
    public ResponseEntity<Administrador> logar(@RequestBody AdministradorDTO dto) {
        Administrador administrador = repository.findTopByNomeAndSenha(dto.getNome(), dto.getSenha());
        Assert.notNull(administrador, "Nome ou Senha Inválidos!");
        return ResponseRest.object(administrador);
    }

    @GetMapping("/cadastroInicial/{senha}")
    public ResponseEntity<Administrador> cadastroInicial(@PathVariable("senha") Integer senha) {
        Assert.isTrue(senha.equals(2000), "Acesso negado!");
        return ResponseRest.ok(service.cadastrarAdmInicial(new Administrador()));
    }

    @PostMapping
    public ResponseEntity<Administrador> cadastrar(@RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        repository.save(this.build.build(new Administrador(), dto));
        return ResponseRest.created("Administrador cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscar(@PathVariable("id") Administrador entity) {
        security.check(entity.getSobrenome(), entity.getToken());
        Assert.notNull(entity, "Administrador não encontrado.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> alterar(@PathVariable("id") Administrador entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Endereço não encontrado.");
        repository.save(this.build.build(new Administrador(), dto));
        return ResponseRest.ok("Administrador alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Administrador> deletar(@PathVariable("id") Administrador entity) {
        security.check(entity.getSobrenome(), entity.getToken());
        Assert.notNull(entity, "Administrador não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Administrador excluído com suecesso.");
    }
}
