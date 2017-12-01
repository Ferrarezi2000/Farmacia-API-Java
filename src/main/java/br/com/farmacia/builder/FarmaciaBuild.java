package br.com.farmacia.builder;

import br.com.farmacia.dto.FarmaciaDTO;
import br.com.farmacia.model.Farmacia;
import org.springframework.stereotype.Component;

@Component
public class FarmaciaBuild {

    public Farmacia build(Farmacia farmacia, FarmaciaDTO dto) {
        if (dto.getId() == null) {
            farmacia.setAtivo(true);
        }
        farmacia.setNome(dto.getNome());
        farmacia.setLocalidade(dto.getLocalidade());

        return farmacia;
    }
}
