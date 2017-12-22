package br.com.farmacia.controller;

import br.com.farmacia.builder.FarmaciaEditarBuild;
import br.com.farmacia.builder.cadastro.FormFarmaciaBuildCompleto;
import br.com.farmacia.config.Security;
import br.com.farmacia.dto.AdministradorDTO;
import br.com.farmacia.dto.FarmaciaDTO;
import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.service.FarmaciaAPPService;
import br.com.farmacia.service.FarmaciaService;
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
    @Autowired private FormFarmaciaBuildCompleto farmaciaBuildCadastro;
    @Autowired private FarmaciaService service;
    @Autowired private Security security;
    @Autowired private FarmaciaAPPService farmaciaAPPService;
    @Autowired private FarmaciaEditarBuild farmaciaEditarBuild;

    @GetMapping
    public ResponseEntity<List<Farmacia>> listar() {
        return ResponseRest.list(service.findAllMedia());
    }

    @GetMapping("/vip")
    public ResponseEntity<?> vip(){
        List<Farmacia> farmacias = service.addEndeContato();
        return ResponseRest.list(farmacias);
    }

    @GetMapping("/avaliacoes")
    public ResponseEntity<?> avaliacoes(@PathVariable("id") Farmacia entity) {
        Assert.notNull(entity, "Farmácia não encontrada.");
        FormCompletoDTO completoDTO = service.completo(entity);
        return ResponseRest.object(completoDTO);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody FormCompletoDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        farmaciaBuildCadastro.build(dto);
        return ResponseRest.ok("Farmácia cadastrada com sucesso.");
    }

    @PostMapping("/editar/perfilApp/{id}")
    public ResponseEntity<?> perfilApp(@PathVariable("id")Farmacia farmacia, @RequestBody FarmaciaDTO dto){
        return ResponseRest.object(farmaciaEditarBuild.buildPerfil(farmacia, dto));
    }

    @PostMapping("/editar/contatosApp/{id}")
    public ResponseEntity<?> contatosApp(@PathVariable("id")Farmacia farmacia, @RequestBody FarmaciaDTO dto) {
        farmaciaEditarBuild.buildContatos(farmacia, dto);
        return ResponseRest.ok("Contatos atualizados com sucesso!");
    }

    @PostMapping("/editar/enderecoApp/{id}")
    public ResponseEntity<?> enderecoApp(@PathVariable("id")Farmacia farmacia, @RequestBody FarmaciaDTO dto) {
        farmaciaEditarBuild.buildEndereco(farmacia, dto);
        return ResponseRest.ok("Endereço atualizado com sucesso!");
    }

    @PostMapping("/editar/adicionalApp/{id}")
    public ResponseEntity<?> adicionalApp(@PathVariable("id")Farmacia farmacia, @RequestBody FarmaciaDTO dto) {
        farmaciaEditarBuild.buildAdicional(farmacia, dto);
        return ResponseRest.ok("Adicional atualizado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Farmacia entity) {
        Assert.notNull(entity, "Farmácia não encontrada.");
        FormCompletoDTO completoDTO = service.completo(entity);
        return ResponseRest.object(completoDTO);
    }

    @GetMapping("/buscarApp/{id}")
    public ResponseEntity<?> buscarApp(@PathVariable("id") Farmacia entity) {
        Assert.notNull(entity, "Farmacia não encontrado.");
        return ResponseRest.object(farmaciaAPPService.build(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmacia> alterar(@PathVariable("id") Farmacia entity, @RequestBody FormCompletoDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Farmácia não encontrada.");
        farmaciaBuildCadastro.build(dto);
        return ResponseRest.ok("Farmácia alterada com sucesso!");
    }

    @GetMapping("/setarAcesso/{id}")
    public ResponseEntity<?> setarAvesso(@PathVariable("id") Farmacia entity) {
        Assert.notNull(entity, "Farmácia não encontrada.");
        service.setarAcesso(entity);
        return ResponseRest.ok("Acesso contabilizado...");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Farmacia> deletar(@PathVariable("id") Farmacia entity, @RequestBody AdministradorDTO dto) {
        security.check(dto.getAdministradorSobrenome(), dto.getAdministradorToken());
        Assert.notNull(entity, "Farmácia não encontrada.");
        repository.delete(entity);
        return ResponseRest.ok("Farmácia excluída com suecesso.");
    }

}
