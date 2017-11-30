package br.com.farmacia.controller;

import br.com.farmacia.builder.AdministradorBuild;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.model.Administrador;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.AdministradorRepository;
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
    @Autowired private AdministradorBuild build;

    @GetMapping
    public ResponseEntity<List<Administrador>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Administrador> cadastrar(@RequestBody AdministradorDTO dto) {
        repository.save(this.build.build(new Administrador(), dto));
        return ResponseRest.created("Administrador cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscar(@PathVariable("id") Administrador entity) {
        Assert.notNull(entity, "Administrador não encontrado.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> alterar(@PathVariable("id") Administrador entity, @RequestBody AdministradorDTO dto) {
        Assert.notNull(entity, "Endereço não encontrado.");
        repository.save(this.build.build(new Administrador(), dto));
        return ResponseRest.ok("Administrador alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Administrador> deletar(@PathVariable("id") Administrador entity) {
        Assert.notNull(entity, "Administrador não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Administrador excluído com suecesso.");
    }
}