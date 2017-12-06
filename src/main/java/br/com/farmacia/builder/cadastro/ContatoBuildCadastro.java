package br.com.farmacia.builder.cadastro;

import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContatoBuildCadastro {

    @Autowired private FarmaciaRepository farmaciaRepository;

    public Contato build(Contato contato, FormCompletoDTO dto, Farmacia farmacia) {

        contato.setAtivo(dto.getAtivo());
        contato.setFarmacia(farmacia);
        contato.setTexto(dto.getTexto());
        contato.setTipo(dto.getTipo());

        return contato;
    }
}
