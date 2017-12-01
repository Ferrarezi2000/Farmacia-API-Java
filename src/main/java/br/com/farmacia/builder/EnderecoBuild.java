package br.com.farmacia.builder;

import br.com.farmacia.dto.EnderecoDTO;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;

    public Endereco build(Endereco endereco, EnderecoDTO dto) {
        Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());

        if (dto.getId() == null) {
            endereco.setAtivo(true);
        }

        endereco.setBairro(dto.getBairro());
        endereco.setFarmacia(farmacia);
        endereco.setLougradouro(dto.getLougradouro());
        endereco.setNumero(dto.getNumero());

        return endereco;
    }
}
