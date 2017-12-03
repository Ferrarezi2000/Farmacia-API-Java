package br.com.farmacia.builder;

import br.com.farmacia.dto.FarmaciaDTO;
import br.com.farmacia.model.Farmacia;
import org.springframework.stereotype.Component;

@Component
public class FarmaciaBuild {

    public Farmacia build(Farmacia farmacia, FarmaciaDTO dto) {

        if (dto.getId() == null) {
            farmacia.setAtivo(true);
        } else {
            farmacia.setAtivo(dto.getAtivo());
        }

        farmacia.setNome(dto.getNome());
        farmacia.setLocalidade(dto.getLocalidade());
        farmacia.setVip(dto.getVip());

        return farmacia;
    }
}
