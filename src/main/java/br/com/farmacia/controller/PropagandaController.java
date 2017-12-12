package br.com.farmacia.controller;

import br.com.farmacia.anotacao.Boleano;
import br.com.farmacia.builder.PropagandaBuild;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.dto.DiasDTO;
import br.com.farmacia.dto.PropagandaDTO;
import br.com.farmacia.model.Propaganda;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.PropagandaRepository;
import br.com.farmacia.service.PropagandaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/propaganda")
@Api(description = "Propaganda")
public class PropagandaController extends AbstractRestController{

    @Autowired private PropagandaRepository repository;
    @Autowired private PropagandaService propagandaService;
    @Autowired private PropagandaBuild build;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Propaganda>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Propaganda> cadastrar(@RequestBody PropagandaDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        build.build(new Propaganda(), dto);
        return ResponseRest.ok("Propaganda cadastrada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propaganda> buscar(@PathVariable("id") Propaganda entity) {
        Assert.notNull(entity, "Propaganda não encontrado.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propaganda> alterar(@PathVariable("id") Propaganda entity, @RequestBody PropagandaDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Patrocinador não encontrado.");
        build.build(entity, dto);
        return ResponseRest.ok("Patrocinador alterado com sucesso!");
    }

    @PostMapping("/deletar/{id}")
    public ResponseEntity<Propaganda> deletar(@PathVariable("id") Propaganda entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Propaganda não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Propaganda excluído com suecesso.");
    }

    @PostMapping("/dias")
    public ResponseEntity<?> porDia(@RequestBody DiasDTO dto) {
        return ResponseRest.object( propagandaService.retornarPropagandaDia(dto));
    }

}
