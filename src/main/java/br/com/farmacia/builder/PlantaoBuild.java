package br.com.farmacia.builder;

import br.com.farmacia.dto.PlantaoDTO;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Plantao;
import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlantaoBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;

    public Plantao build(Plantao plantao, PlantaoDTO dto) {
        Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
        plantao.setData(dto.getData());
        plantao.setFarmacia(farmacia);

        return plantao;
    }
}
