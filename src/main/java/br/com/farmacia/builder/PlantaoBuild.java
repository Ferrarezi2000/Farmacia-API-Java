package br.com.farmacia.builder;

import br.com.farmacia.dto.PlantaoDTO;
import br.com.farmacia.model.Calendario;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Plantao;
import br.com.farmacia.repository.CalendarioRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlantaoBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private CalendarioRepository calendarioRepository;

    public Plantao build(Plantao plantao, PlantaoDTO dto) {
        Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
        plantao.setFarmacia(farmacia);

        Calendario calendario = calendarioRepository.findOne(dto.getCalendarioId());
        plantao.setCalendario(calendario);

        return plantao;
    }
}
