package br.com.farmacia.builder;

import br.com.farmacia.dto.PropagandaDTO;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.model.Propaganda;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import br.com.farmacia.repository.PropagandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropagandaBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;
    @Autowired private PropagandaRepository propagandaRepository;

    public Propaganda build(Propaganda propaganda, PropagandaDTO dto) {

        if (dto.getFarmaciaId() != null) {
            Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
            propaganda.setFarmacia(farmacia);
        } else {
            Patrocinador patrocinador = patrocinadorRepository.findOne(dto.getPatrocinadorId());
            propaganda.setPatrocinador(patrocinador);
        }

        propaganda.setDiaSemana(dto.getDiaSemana());
        propagandaRepository.save(propaganda);

        return propaganda;
    }
}
