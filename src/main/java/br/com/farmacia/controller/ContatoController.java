package br.com.farmacia.controller;

import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.dto.ContatoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.ContatoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/contato")
@Api(description = "Contato")
public class ContatoController extends AbstractRestController{

    @Autowired private ContatoRepository repository;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Contato>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscar(@PathVariable("id") Contato entity) {
        Assert.notNull(entity, "Contato não encontrado.");
        return ResponseRest.object(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> deletar(@PathVariable("id") Contato entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Contato não encontrado.");
        repository.delete(entity);
        return ResponseRest.ok("Contato excluído com suecesso.");
    }
}
