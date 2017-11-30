package br.com.farmacia.builder;

import br.com.farmacia.dto.EnderecoDTO;
import br.com.farmacia.dto.FarmaciaDTO;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoBuild {

    @Autowired private FarmaciaService service;
    @Autowired private FarmaciaRepository farmaciaRepository;

    public Endereco build(Endereco endereco, EnderecoDTO dto) {
        Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
        endereco.setAtivo(dto.getAtivo());
        endereco.setBairro(dto.getBairro());
        endereco.setFarmacia(farmacia);
        endereco.setLougradouro(dto.getLougradouro());
        endereco.setNumero(dto.getNumero());

        return endereco;
    }
}
