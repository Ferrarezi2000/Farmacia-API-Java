package br.com.farmacia.controller;

import br.com.farmacia.builder.FormaPagamentoBuild;
import br.com.farmacia.dto.FormaPagamentoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.FormaPagamento;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.FormaPagamentoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pagamento")
@Api(description = "Pagamento")
public class FormaPagamentoController extends AbstractRestController{

    @Autowired private FormaPagamentoRepository repository;
    @Autowired private FormaPagamentoBuild build;

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscar(@PathVariable("id") FormaPagamento entity) {
        Assert.notNull(entity, "Forma Pagamento não encontrado.");
        return ResponseRest.object(entity);
    }

    @PostMapping()
    public ResponseEntity<?> cadastro(@RequestBody FormaPagamentoDTO dto){
        build.build(new FormaPagamento(), dto);
        return ResponseRest.ok("Forma Pagamento Cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") FormaPagamento entity, @RequestBody FormaPagamentoDTO dto) {
        Assert.notNull(entity, "Forma Pagamento não encontrado.");
        build.build(entity, dto);
        return ResponseRest.ok("Forma Pagamento alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") FormaPagamento entity) {
        Assert.notNull(entity, "Forma Pagamento não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Forma Pagamento excluído com suecesso.");
    }
}
