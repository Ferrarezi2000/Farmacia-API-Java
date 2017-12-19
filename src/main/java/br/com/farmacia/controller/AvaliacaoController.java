package br.com.farmacia.controller;

import br.com.farmacia.builder.AvaliacaoBuild;
import br.com.farmacia.dto.AvaliacaoDTO;
import br.com.farmacia.model.Avaliacao;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.AvaliacaoRepository;
import br.com.farmacia.service.AvaliacaoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/avaliacao")
@Api(description = "Avaliação")
public class AvaliacaoController extends AbstractRestController{

    @Autowired private AvaliacaoRepository repository;
    @Autowired private AvaliacaoBuild build;
    @Autowired private AvaliacaoService service;

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscar(@PathVariable("id") Avaliacao entity) {
        Assert.notNull(entity, "Avaliacão não encontrada.");
        return ResponseRest.object(entity);
    }

    @PostMapping()
    public ResponseEntity<?> cadastro(@RequestBody AvaliacaoDTO dto){
        build.build(new Avaliacao(), dto);
        return ResponseRest.ok("Avaliação Cadastrada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Avaliacao entity, @RequestBody AvaliacaoDTO dto) {
        Assert.notNull(entity, "Avaliação não encontrada.");
        build.build(entity, dto);
        return ResponseRest.ok("Avaliação alterada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Avaliacao entity) {
        Assert.notNull(entity, "Avaliacão não encontrada.");
        repository.delete(entity);
        return ResponseRest.ok("Avaliacão excluída com suecesso.");
    }

    @PutMapping("/resposta/{id}")
    public ResponseEntity<?> resposta(@PathVariable("id") Avaliacao entity, @RequestBody AvaliacaoDTO dto) {
        Assert.notNull(entity, "Avaliacão não encontrada.");
        service.setarResposta(entity, dto);
        return ResponseRest.ok("Sua resposta foi cadastrada com sucesso!");
    }
}
