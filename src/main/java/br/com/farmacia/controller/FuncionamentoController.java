package br.com.farmacia.controller;

import br.com.farmacia.builder.FormaPagamentoBuild;
import br.com.farmacia.builder.FuncionamentoBuild;
import br.com.farmacia.dto.FormaPagamentoDTO;
import br.com.farmacia.dto.FuncionamentoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.FormaPagamento;
import br.com.farmacia.model.Funcionamento;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.FormaPagamentoRepository;
import br.com.farmacia.repository.FuncionamentoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/funcionamento")
@Api(description = "Funcionamento")
public class FuncionamentoController extends AbstractRestController{

    @Autowired private FuncionamentoRepository repository;
    @Autowired private FuncionamentoBuild build;

    @GetMapping
    public ResponseEntity<List<Funcionamento>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionamento> buscar(@PathVariable("id") Funcionamento entity) {
        Assert.notNull(entity, "Funcionamento não encontrado.");
        return ResponseRest.object(entity);
    }

    @PostMapping()
    public ResponseEntity<?> cadastro(@RequestBody FuncionamentoDTO dto){
        build.build(new Funcionamento(), dto);
        return ResponseRest.ok("Funcionamento Cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Funcionamento entity, @RequestBody FuncionamentoDTO dto) {
        Assert.notNull(entity, "Funcionamento não encontrado.");
        build.build(entity, dto);
        return ResponseRest.ok("Funcionamento com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Funcionamento entity) {
        Assert.notNull(entity, "Funcionamento não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Funcionamento com suecesso.");
    }
}
