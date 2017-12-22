package br.com.farmacia.builder;

import br.com.farmacia.dto.FarmaciaDTO;
import br.com.farmacia.model.Adicional;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.repository.AdicionalRepository;
import br.com.farmacia.repository.ContatoRepository;
import br.com.farmacia.repository.EnderecoRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FarmaciaEditarBuild {

    @Autowired private ContatoRepository contatoRepository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private AdicionalRepository adicionalRepository;
    @Autowired private FarmaciaRepository farmaciaRepository;

    public Farmacia buildPerfil(Farmacia farmacia, FarmaciaDTO dto) {

        farmacia.setTexto(dto.getTexto());
        farmacia.setDelivery(dto.getDelivery());
        farmacia.setSenhaAcesso(dto.getSenhaAcesso());
        farmacia.setUsuarioAcesso(dto.getUsuarioAcesso());

        return farmacia;
    }

    public void buildEndereco(Farmacia farmacia, FarmaciaDTO dto) {

        Endereco endereco = enderecoRepository.findTopByFarmacia(farmacia);
        endereco.setLogradouro(dto.getEndereco().getLogradouro());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setBairro(dto.getEndereco().getBairro());
        enderecoRepository.save(endereco);

    }

    public void buildAdicional(Farmacia farmacia, FarmaciaDTO dto) {

        farmacia.setTituloAdicionais(dto.getTituloAdicionais());
        farmacia.setTextoAdicionais(dto.getTextoAdicionais());
        farmaciaRepository.save(farmacia);

        List<Adicional> adicionais = adicionalRepository.findAllByFarmacia(farmacia);
        if (adicionais.size() != 0 && adicionais != null) {
            adicionais.forEach(adicional -> adicionalRepository.delete(adicional));
        }

        dto.getListaAdicionais().forEach(adicional -> {
            Adicional adicionalNovo = new Adicional();
            adicionalNovo.setFarmacia(farmacia);
            adicionalNovo.setItem(adicional.getItem());
            adicionalNovo.setSubItem(adicional.getSubItem());
            adicionalNovo.setValor(adicional.getValor());
            adicionalRepository.save(adicionalNovo);
        });

    }

    public void buildContatos(Farmacia farmacia, FarmaciaDTO dto) {

        List<Contato> contatos = contatoRepository.findAllByFarmacia(farmacia);
        if (contatos.size() != 0 && contatos != null) {
            contatos.forEach(contato -> contatoRepository.delete(contato));
        }

        dto.getContatos().forEach(contato -> {
            Contato contatoNovo = new Contato();
            contatoNovo.setFarmacia(farmacia);
            contatoNovo.setNumero(contato.getNumero());
            contatoNovo.setTipo(contato.getTipo());
            contatoRepository.save(contatoNovo);
        });

    }
}
