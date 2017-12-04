package br.com.farmacia.builder;

import br.com.farmacia.dto.PatrocinadorDTO;
import br.com.farmacia.model.Administrador;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatrocinadorBuild {

    @Autowired private AdministradorRepository administradorRepository;

    public Patrocinador build(Patrocinador patrocinador, PatrocinadorDTO dto) {
        Administrador administrador = administradorRepository.findOne(dto.getAdministradorId());

        if (dto.getId() == null) {
            patrocinador.setAtivo(true);
        } else {
            patrocinador.setAtivo(dto.getAtivo());
        }
        patrocinador.setValorMensal(dto.getValorMensal());
        patrocinador.setAdministrador(administrador);
        patrocinador.setNome(dto.getNome());

        return patrocinador;
    }
}
