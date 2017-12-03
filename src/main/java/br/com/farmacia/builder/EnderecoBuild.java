package br.com.farmacia.builder;

import br.com.farmacia.dto.EnderecoDTO;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;

    public Endereco build(Endereco endereco, EnderecoDTO dto) {

        if (dto.getFarmaciaId() != null){
            Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
            endereco.setFarmacia(farmacia);
        } else {
            Patrocinador patrocinador = patrocinadorRepository.findOne(dto.getPatrocinadorId());
            endereco.setPatrocinador(patrocinador);
        }

        if (dto.getId() == null) {
            endereco.setAtivo(true);
        } else {
            endereco.setAtivo(dto.getAtivo());
        }

        endereco.setBairro(dto.getBairro());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());

        return endereco;
    }
}
