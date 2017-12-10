package br.com.farmacia.builder;

import br.com.farmacia.dto.ContatoDTO;
import br.com.farmacia.dto.PropagandaDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.model.Propaganda;
import br.com.farmacia.repository.ContatoRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import br.com.farmacia.repository.PropagandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContatoBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;

    public Contato build(Contato contato, ContatoDTO dto) {

        if (dto.getFarmaciaId() != null) {
            Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
            contato.setFarmacia(farmacia);
        } else {
            Patrocinador patrocinador = patrocinadorRepository.findOne(dto.getPatrocinadorId());
            contato.setPatrocinador(patrocinador);
        }

        contato.setAtivo(dto.getAtivo());
        contato.setTexto(dto.getTexto());
        contato.setTipo(dto.getTipo());
        contatoRepository.save(contato);

        return contato;
    }
}
