package br.com.farmacia.service;

import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.repository.ContatoRepository;
import br.com.farmacia.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmaciaService {

    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;

    public FormCompletoDTO completo(Farmacia farmacia) {

        FormCompletoDTO completoDTO = new FormCompletoDTO();
        completoDTO.setFarmaciaId(farmacia.getId());
        completoDTO.setFarmaciaAtivo(farmacia.getAtivo());
        completoDTO.setFarmaciaLocalidade(farmacia.getLocalidade());
        completoDTO.setFarmaciaNome(farmacia.getNome());
        completoDTO.setFarmaciaValorMensal(farmacia.getValorMensal());
        completoDTO.setFarmaciaVip(farmacia.getVip());
        if (farmacia.getAdministrador() != null) {
            completoDTO.setFarmaciaAdministradorId(farmacia.getAdministrador().getId());
        }

        Endereco endereco = enderecoRepository.findTopByFarmacia(farmacia);
        completoDTO.setEnderecoId(endereco.getId());
        completoDTO.setEnderecoAtivo(endereco.getAtivo());
        completoDTO.setEnderecoBairro(endereco.getBairro());
        completoDTO.setEnderecoFarmaciaId(farmacia.getId());
        completoDTO.setEnderecoLogradouro(endereco.getLogradouro());
        completoDTO.setEnderecoNumero(endereco.getNumero());

        Contato contato = contatoRepository.findTopByFarmacia(farmacia);
        completoDTO.setContatoId(contato.getId());
        completoDTO.setContatoAtivo(contato.getAtivo());
        completoDTO.setContatoTexto(contato.getTexto());
        completoDTO.setContatoTipo(contato.getTipo());

        return completoDTO;
    }

}
