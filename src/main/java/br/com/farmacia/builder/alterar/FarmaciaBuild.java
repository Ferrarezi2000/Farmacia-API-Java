package br.com.farmacia.builder.alterar;

import br.com.farmacia.dto.FarmaciaDTO;
import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.Administrador;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FarmaciaBuild {

    @Autowired private AdministradorRepository administradorRepository;

    public Farmacia build(Farmacia farmacia, FarmaciaDTO dto) {
        if (dto.getAdministradorId() != null) {
            Administrador administrador = administradorRepository.findOne(dto.getAdministradorId());
            farmacia.setAdministrador(administrador);
        }
        farmacia.setAtivo(dto.getAtivo());
        farmacia.setValorMensal(dto.getValorMensal());
        farmacia.setNome(dto.getNome());
        farmacia.setLocalidade(dto.getLocalidade());
        farmacia.setVip(dto.getVip());

        return farmacia;
    }
}
