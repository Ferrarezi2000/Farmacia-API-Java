package br.com.farmacia.builder;

import br.com.farmacia.dto.FarmaciaDTO;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FarmaciaBuild {

    @Autowired private FarmaciaService service;

    public Farmacia build(Farmacia farmacia, FarmaciaDTO dto) {
        farmacia.setAtivo(dto.getAtivo());
        farmacia.setNome(dto.getNome());

        return farmacia;
    }
}
