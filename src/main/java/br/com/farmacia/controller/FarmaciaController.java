package br.com.farmacia.controller;

import br.com.farmacia.builder.alterar.FarmaciaBuild;
import br.com.farmacia.builder.cadastro.ContatoBuildCadastro;
import br.com.farmacia.builder.cadastro.EnderecoBuildCadastro;
import br.com.farmacia.builder.cadastro.FarmaciaBuildCadastro;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.dto.FarmaciaDTO;
import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.ContatoRepository;
import br.com.farmacia.repository.EnderecoRepository;
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
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private FarmaciaBuildCadastro farmaciaBuildCadastro;
    @Autowired private EnderecoBuildCadastro enderecoBuildCadastro;
    @Autowired private ContatoBuildCadastro contatoBuildCadastro;

    @Autowired private FarmaciaBuild build;
    @Autowired private Security security;

    @GetMapping
    public ResponseEntity<List<Farmacia>> listar() {
        return ResponseRest.list(repository.findAll());
    }

    @GetMapping("/vip")
    public ResponseEntity<List<Farmacia>> vip(){
        return ResponseRest.list(repository.findAllByVipIsTrue());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody FormCompletoDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Farmacia farmacia = repository.save(this.farmaciaBuildCadastro.build(new Farmacia(), dto));
        enderecoRepository.save(enderecoBuildCadastro.build(new Endereco(), dto, farmacia));
        contatoRepository.save(contatoBuildCadastro.build(new Contato(), dto, farmacia));
        return ResponseRest.ok("Farmácia cadastrada com sucesso.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farmacia> buscar(@PathVariable("id") Farmacia entity) {
        Assert.notNull(entity, "Farmácia não encontrada.");
        return ResponseRest.object(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmacia> alterar(@PathVariable("id") Farmacia entity, @RequestBody FarmaciaDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Farmácia não encontrada.");
        repository.save(this.build.build(new Farmacia(), dto));
        return ResponseRest.ok("Farmácia alterada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Farmacia> deletar(@PathVariable("id") Farmacia entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Farmácia não encontrada.");
        repository.delete(entity);
        return ResponseRest.ok("Farmácia excluída com suecesso.");
    }

}
