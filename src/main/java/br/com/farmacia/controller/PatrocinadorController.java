package br.com.farmacia.controller;

import br.com.farmacia.builder.cadastro.FormPatrocinadorBuildCompleto;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.dto.FormCompletoDTO;
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
    @Autowired private FormPatrocinadorBuildCompleto build;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Patrocinador>> listar() {
        return ResponseRest.list(repository.findAllByAtivoIsTrue());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody FormCompletoDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        build.build(dto);
        return ResponseRest.created("Patrocinador cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrocinador> buscar(@PathVariable("id") Patrocinador entity) {
        Assert.notNull(entity, "Patrocinador não encontrado.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Patrocinador entity, @RequestBody FormCompletoDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Patrocinador não encontrado.");
        build.build(dto);
        return ResponseRest.ok("Patrocinador alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patrocinador> deletar(@PathVariable("id") Patrocinador entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Patrocinador não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Patrocinador excluído com suecesso.");
    }

}
