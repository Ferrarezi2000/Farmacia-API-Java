package br.com.farmacia.controller;

import br.com.farmacia.builder.CalendarioBuild;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.dto.CalendarioDTO;
import br.com.farmacia.dto.PreencherCalendarioDTO;
import br.com.farmacia.model.Calendario;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.CalendarioRepository;
import br.com.farmacia.service.CalendarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/calendario")
@Api(description = "Calendario")
public class CalendarioController extends AbstractRestController{

    @Autowired private CalendarioRepository repository;
    @Autowired private CalendarioService service;
    @Autowired private CalendarioBuild build;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Calendario>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Calendario> cadastrar(@RequestBody CalendarioDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        repository.save(this.build.build(new Calendario(), dto));
        return ResponseRest.created("Calendario cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calendario> buscar(@PathVariable("id") Calendario entity) {
        Assert.notNull(entity, "Calendario não encontrado.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calendario> alterar(@PathVariable("id") Calendario entity, @RequestBody CalendarioDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Calendario não encontrado.");
        repository.save(this.build.build(new Calendario(), dto));
        return ResponseRest.ok("Calendario alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Calendario> deletar(@PathVariable("id") Calendario entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Calendario não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Calendario excluído com suecesso.");
    }

    @PostMapping("/comporCalendario")
    public ResponseEntity<?> comporCalendario(@RequestBody PreencherCalendarioDTO dto){
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        service.comporCalendario(dto);
        return ResponseRest.ok("Completo");
    }
}
