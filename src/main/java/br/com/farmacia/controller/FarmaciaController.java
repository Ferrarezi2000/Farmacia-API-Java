package br.com.farmacia.controller;

import br.com.farmacia.builder.FarmaciaBuild;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.FarmaciaDTO;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.FarmaciaRepository;
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
    @Autowired private FarmaciaBuild build;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Farmacia>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Farmacia> cadastrar(@RequestBody FarmaciaDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        repository.save(this.build.build(new Farmacia(), dto));
        return ResponseRest.created("Farmácia cadastrada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farmacia> buscar(@PathVariable("id") Farmacia entity) {
        Assert.notNull(entity, "Farmácia não encontrada.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmacia> alterar(@PathVariable("id") Farmacia entity, @RequestBody FarmaciaDTO dto) {
        Assert.notNull(entity, "Farmácia não encontrada.");
        repository.save(this.build.build(new Farmacia(), dto));
        return ResponseRest.ok("Farmácia alterada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Farmacia> deletar(@PathVariable("id") Farmacia entity) {
        Assert.notNull(entity, "Farmácia não encontrada.");
        repository.delete(entity);
        return ResponseRest.ok("Farmácia excluída com suecesso.");
    }

}
