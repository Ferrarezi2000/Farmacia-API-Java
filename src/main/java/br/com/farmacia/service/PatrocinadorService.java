package br.com.farmacia.service;

import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.ContatoRepository;
import br.com.farmacia.repository.EnderecoRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatrocinadorService {

    @Autowired private PatrocinadorRepository repository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;

    public FormCompletoDTO completo(Patrocinador patrocinador) {

        FormCompletoDTO completoDTO = new FormCompletoDTO();
        completoDTO.setPatrocinadorId(patrocinador.getId());
        completoDTO.setPatrocinadorAtivo(patrocinador.getAtivo());
        completoDTO.setPatrocinadorNome(patrocinador.getNome());
        completoDTO.setPatrocinadorValorMensal(patrocinador.getValorMensal());
        if (patrocinador.getAdministrador() != null) {
            completoDTO.setPatrocinadorAdministradorId(patrocinador.getAdministrador().getId());
        }

        Endereco endereco = enderecoRepository.findTopByPatrocinador(patrocinador);
        completoDTO.setEnderecoId(endereco.getId());
        completoDTO.setEnderecoAtivo(endereco.getAtivo());
        completoDTO.setEnderecoBairro(endereco.getBairro());
        completoDTO.setEnderecoPatrocinadorId(patrocinador.getId());
        completoDTO.setEnderecoLogradouro(endereco.getLogradouro());
        completoDTO.setEnderecoNumero(endereco.getNumero());

        Contato contato = contatoRepository.findTopByPatrocinador(patrocinador);
        completoDTO.setContatoId(contato.getId());
        completoDTO.setContatoAtivo(contato.getAtivo());
        completoDTO.setContatoTexto(contato.getTexto());
        completoDTO.setContatoTipo(contato.getTipo());

        return completoDTO;
    }

}
