package br.com.farmacia.service;

import br.com.farmacia.model.Administrador;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.AdministradorRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AdministradorService {

    @Autowired private AdministradorRepository repository;
    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;

    public String cadastrarAdmInicial(Administrador administrador) {
        Administrador administradorcheck = repository.findTopByNomeAndSobrenome("Thiago", "Ferrarezi");

        if (administradorcheck == null) {
            administrador.setAtivo(true);
            administrador.setNome("Thiago");
            administrador.setSobrenome("Ferrarezi");
            administrador.setSenha("2000");
            UUID uuid = UUID.randomUUID();
            String token = uuid.toString();
            administrador.setToken(token);
            repository.save(administrador);

            return "Administrador cadastrado com sucesso...";
        } else {
            return "Falha... Administrador já cadastrado...";
        }
    }

    public void setarFarmaciaPatrocinador(Administrador administrador) {
       List<Farmacia> farmacias = farmaciaRepository.findAllByAdministrador(administrador);
        if (farmacias.size() != 0) {
            farmacias.forEach(farmacia -> farmacia.setAdministrador(null));
            Double somaTotalFarmacia = farmacias.stream().mapToDouble(f -> f.getValorMensal().doubleValue()).sum();
            administrador.setFarmacias(farmacias);
            administrador.setTotalValorFarmacia(somaTotalFarmacia);
        }

        List<Patrocinador> patrocinadores = patrocinadorRepository.findAllByAdministrador(administrador);
        if (patrocinadores.size() != 0) {
            patrocinadores.forEach(patrocinador -> patrocinador.setAdministrador(null));
            Double somaTotalPatrocinador = patrocinadores.stream().mapToDouble(p -> p.getValorMensal().doubleValue()).sum();
            administrador.setPatrocinadores(patrocinadores);
            administrador.setTotalValorPatrocinador(somaTotalPatrocinador);
        }
    }
}
