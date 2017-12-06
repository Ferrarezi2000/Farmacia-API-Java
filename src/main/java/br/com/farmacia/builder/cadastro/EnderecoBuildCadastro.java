package br.com.farmacia.builder.cadastro;

import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import org.springframework.stereotype.Component;

@Component
public class EnderecoBuildCadastro {


    public Endereco build(Endereco endereco, FormCompletoDTO dto, Farmacia farmacia) {

        endereco.setFarmacia(farmacia);
        endereco.setAtivo(dto.getAtivo());
        endereco.setBairro(dto.getBairro());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());

        return endereco;
    }
}
