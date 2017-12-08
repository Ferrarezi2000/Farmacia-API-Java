package br.com.farmacia.controller;

import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.EnderecoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/endereco")
@Api(description = "Endereco")
public class EnderecoController extends AbstractRestController{

    @Autowired private EnderecoRepository repository;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Endereco>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscar(@PathVariable("id") Endereco entity) {
        Assert.notNull(entity, "Endereco não encontrado.");
        return ResponseRest.object(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> deletar(@PathVariable("id") Endereco entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Endereço não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Endereço excluído com suecesso.");
    }

}
