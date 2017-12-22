package br.com.farmacia.builder;

import br.com.farmacia.dto.AdicionalDTO;
import br.com.farmacia.model.Adicional;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.AdicionalRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdicionalBuild {

    @Autowired private AdicionalRepository adicionalRepository;
    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;

    public Adicional build(Adicional adicional, AdicionalDTO dto) {

        if (dto.getFarmaciaId() != null) {
            Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
            adicional.setFarmacia(farmacia);
        } else {
            Patrocinador patrocinador = patrocinadorRepository.findOne(dto.getPatrocinadorId());
            adicional.setPatrocinador(patrocinador);
        }

        adicional.setItem(dto.getItem());
        adicional.setValor(dto.getValor());
        adicional.setSubItem(dto.getSubItem());
        adicionalRepository.save(adicional);

        return adicional;
    }
}
