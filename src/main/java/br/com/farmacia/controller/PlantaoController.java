package br.com.farmacia.controller;

import br.com.farmacia.builder.PlantaoBuild;
import br.com.farmacia.dto.PlantaoDTO;
import br.com.farmacia.model.Plantao;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.PlantaoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/plantao")
@Api(description = "Plantão")
public class PlantaoController extends AbstractRestController{

    @Autowired private PlantaoRepository repository;
    @Autowired private PlantaoBuild build;

    @GetMapping
    public ResponseEntity<List<Plantao>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Plantao> cadastrar(@RequestBody PlantaoDTO dto) {
        repository.save(this.build.build(new Plantao(), dto));
        return ResponseRest.created("Plantão cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plantao> buscar(@PathVariable("id") Plantao entity) {
        Assert.notNull(entity, "Plantao não encontrado.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plantao> alterar(@PathVariable("id") Plantao entity, @RequestBody PlantaoDTO dto) {
        Assert.notNull(entity, "Endereço não encontrado.");
        repository.save(this.build.build(new Plantao(), dto));
        return ResponseRest.ok("Plantao alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plantao> deletar(@PathVariable("id") Plantao entity) {
        Assert.notNull(entity, "Plantao não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Plantao excluído com suecesso.");
    }

    @GetMapping("/plantoes/{data}")
    public ResponseEntity<?> plantoes(@PathVariable("data") String data) {
        Assert.notNull(data, "Data obrigatória.");
        return ResponseRest.list(repository.findAllByData(data));
    }
}
