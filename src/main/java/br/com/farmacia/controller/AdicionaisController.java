package br.com.farmacia.controller;

import br.com.farmacia.builder.AdicionalBuild;
import br.com.farmacia.dto.AdicionalDTO;
import br.com.farmacia.model.Adicional;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.AdicionalRepository;
import br.com.farmacia.service.AdicionalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/adicional")
@Api(description = "Adicional")
public class AdicionaisController extends AbstractRestController{

    @Autowired private AdicionalRepository repository;
    @Autowired private AdicionalBuild build;
    @Autowired private AdicionalService service;

    @GetMapping
    public ResponseEntity<List<Adicional>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @GetMapping("/farmacia/{id}")
    public ResponseEntity<List<Adicional>> adicionaisFarmacia(@PathVariable("id") Long id) {
        return ResponseRest.list(service.findAllFarmacia(id));
    }

    @GetMapping("/patrocinador/{id}")
    public ResponseEntity<List<Adicional>> adicionaisPatrocinador(@PathVariable("id") Long id) {
        return ResponseRest.list(service.findAllPatrocinador(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adicional> buscar(@PathVariable("id") Adicional entity) {
        Assert.notNull(entity, "Adicional não encontrado.");
        return ResponseRest.object(entity);
    }

    @PostMapping()
    public ResponseEntity<?> cadastro(@RequestBody AdicionalDTO dto){
        build.build(new Adicional(), dto);
        return ResponseRest.ok("Adicional Cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Adicional entity, @RequestBody AdicionalDTO dto) {
        Assert.notNull(entity, "Adicional não encontrado.");
        build.build(entity, dto);
        return ResponseRest.ok("Adicional alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Adicional entity) {
        Assert.notNull(entity, "Adicional não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Adicional excluído com suecesso.");
    }
}
