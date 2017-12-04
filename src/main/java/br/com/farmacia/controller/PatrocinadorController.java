package br.com.farmacia.controller;

import br.com.farmacia.builder.PatrocinadorBuild;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.dto.PatrocinadorDTO;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.PatrocinadorRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patrocinador")
@Api(description = "Patrocinador")
public class PatrocinadorController extends AbstractRestController{

    @Autowired private PatrocinadorRepository repository;
    @Autowired private PatrocinadorBuild build;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Patrocinador>> listar() {
        return ResponseRest.list(repository.findAllByAtivoIsTrue());
    }

    @PostMapping
    public ResponseEntity<Patrocinador> cadastrar(@RequestBody PatrocinadorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        repository.save(this.build.build(new Patrocinador(), dto));
        return ResponseRest.created("Patrocinador cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrocinador> buscar(@PathVariable("id") Patrocinador entity) {
        Assert.notNull(entity, "Patrocinador não encontrado.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrocinador> alterar(@PathVariable("id") Patrocinador entity, @RequestBody PatrocinadorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Patrocinador não encontrado.");
        repository.save(this.build.build(new Patrocinador(), dto));
        return ResponseRest.ok("Calendario alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patrocinador> deletar(@PathVariable("id") Patrocinador entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Patrocinador não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Patrocinador excluído com suecesso.");
    }

}
