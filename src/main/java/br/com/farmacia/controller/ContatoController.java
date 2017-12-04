package br.com.farmacia.controller;

import br.com.farmacia.builder.ContatoBuild;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.dto.ContatoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.ContatoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/contato")
@Api(description = "Contato")
public class ContatoController extends AbstractRestController{

    @Autowired private ContatoRepository repository;
    @Autowired private ContatoBuild build;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Contato>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Contato> cadastrar(@RequestBody ContatoDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        repository.save(this.build.build(new Contato(), dto));
        return ResponseRest.created("Contato cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscar(@PathVariable("id") Contato entity) {
        Assert.notNull(entity, "Contato não encontrado.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> alterar(@PathVariable("id") Contato entity, @RequestBody ContatoDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Endereço não encontrado.");
        repository.save(this.build.build(new Contato(), dto));
        return ResponseRest.ok("Contato alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> deletar(@PathVariable("id") Contato entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Contato não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Contato excluído com suecesso.");
    }
}
