package br.com.farmacia.service;

import br.com.farmacia.model.Adicional;
import br.com.farmacia.repository.AdicionalRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdicionalService {

    @Autowired private AdicionalRepository repository;
    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;

    public List<Adicional> findAllFarmacia(Long id) {
        List<Adicional> adicionais = repository.findAllByFarmacia(farmaciaRepository.findOne(id));
        adicionais.forEach(adicional -> adicional.setFarmacia(null));
        return adicionais;
    }

    public List<Adicional> findAllPatrocinador(Long id) {
        List<Adicional> adicionais = repository.findAllByPatrocinador(patrocinadorRepository.findOne(id));
        adicionais.forEach(adicional -> adicional.setPatrocinador(null));
        return adicionais;
    }
}
