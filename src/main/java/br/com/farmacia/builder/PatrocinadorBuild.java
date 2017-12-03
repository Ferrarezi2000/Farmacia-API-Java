package br.com.farmacia.builder;

import br.com.farmacia.dto.PatrocinadorDTO;
import br.com.farmacia.model.Patrocinador;
import org.springframework.stereotype.Component;

@Component
public class PatrocinadorBuild {

    public Patrocinador build(Patrocinador patrocinador, PatrocinadorDTO dto) {

        if (dto.getId() == null) {
            patrocinador.setAtivo(true);
        } else {
            patrocinador.setAtivo(dto.getAtivo());
        }
        patrocinador.setNome(dto.getNome());

        return patrocinador;
    }
}
