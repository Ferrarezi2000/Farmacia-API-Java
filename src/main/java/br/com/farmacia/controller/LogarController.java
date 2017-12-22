package br.com.farmacia.controller;

import br.com.farmacia.dto.LogarDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.model.ResponseRest;
import br.com.farmacia.repository.ContatoRepository;
import br.com.farmacia.service.LogarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/logar")
@Api(description = "Logar")
public class LogarController extends AbstractRestController{

    @Autowired private LogarService logarService;

    @PostMapping
    public ResponseEntity<?> logar(@RequestBody LogarDTO dto) {
        Farmacia farmacia = logarService.logarFarmacia(dto.getUsuarioAcesso(), dto.getSenhaAcesso());
        if (farmacia != null) {
            return ResponseRest.object(farmacia);
        }
        Patrocinador patrocinador = logarService.logarPatrocinador(dto.getUsuarioAcesso(), dto.getSenhaAcesso());
        if (patrocinador != null) {
            return ResponseRest.object(patrocinador);
        }
        return ResponseRest.ok("Acesso negado!");
    }
}
