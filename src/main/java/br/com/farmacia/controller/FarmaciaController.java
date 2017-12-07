package br.com.farmacia.controller;

import br.com.farmacia.builder.alterar.FarmaciaBuild;
import br.com.farmacia.builder.cadastro.FormFarmaciaBuildCompleto;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.service.FarmaciaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/farmacia")
@Api(description = "Farmacia")
public class FarmaciaController extends AbstractRestController{

    @Autowired private FarmaciaRepository repository;
    @Autowired private FormFarmaciaBuildCompleto farmaciaBuildCadastro;
    @Autowired private FarmaciaService service;
    @Autowired private FarmaciaBuild build;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Farmacia>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @GetMapping("/vip")
    public ResponseEntity<List<Farmacia>> vip(){
        return ResponseRest.list(repository.findAllByVipIsTrue());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody FormCompletoDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        farmaciaBuildCadastro.build(dto);
        return ResponseRest.ok("Farmácia cadastrada com sucesso.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Farmacia entity) {
        Assert.notNull(entity, "Farmácia não encontrada.");
        FormCompletoDTO completoDTO = service.completo(entity);
        return ResponseRest.object(completoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmacia> alterar(@PathVariable("id") Farmacia entity, @RequestBody FormCompletoDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Farmácia não encontrada.");
        farmaciaBuildCadastro.build(dto);
        return ResponseRest.ok("Farmácia alterada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Farmacia> deletar(@PathVariable("id") Farmacia entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Farmácia não encontrada.");
        repository.delete(entity);
        return ResponseRest.ok("Farmácia excluída com suecesso.");
    }

}
