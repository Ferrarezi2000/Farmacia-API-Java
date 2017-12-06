package br.com.farmacia.builder.alterar;

import br.com.farmacia.dto.ContatoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContatoBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;

    public Contato build(Contato contato, ContatoDTO dto) {
        Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());

        contato.setAtivo(dto.getAtivo());
        contato.setFarmacia(farmacia);
        contato.setTexto(dto.getTexto());
        contato.setTipo(dto.getTipo());

        return contato;
    }
}
