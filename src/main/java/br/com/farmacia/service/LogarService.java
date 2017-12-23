package br.com.farmacia.service;

import br.com.farmacia.model.*;
import br.com.farmacia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogarService {

    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;
    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private AdicionalRepository adicionalRepository;

    public Farmacia logarFarmacia(String usuario, String senha) {
        Farmacia farmacia = farmaciaRepository.findTopByUsuarioAcessoAndSenhaAcesso(usuario, senha);
        if (farmacia != null) {

            List<Avaliacao> avaliacoesTotal = avaliacaoRepository.findAllByFarmacia(farmacia);
            Double totalSoma = avaliacoesTotal.stream().mapToDouble(a -> a.getValor()).sum();
            Double media = totalSoma / avaliacoesTotal.size();
            farmacia.setMedia(Math.round(media / 0.5) * 0.5);

            List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByFarmaciaAndComentarioNotNullAndRespostaIsNull(farmacia);
            avaliacoes.forEach(avaliacao -> avaliacao.setFarmacia(null));
            farmacia.setAvaliacoes(avaliacoes);
            farmacia.setAdministrador(null);

            List<Contato> contatos = contatoRepository.findAllByFarmacia(farmacia);
            contatos.forEach(contato -> contato.setFarmacia(null));
            farmacia.setContatos(contatos);

            Endereco endereco = enderecoRepository.findTopByFarmacia(farmacia);
            endereco.setFarmacia(null);
            farmacia.setEndereco(endereco);

            List<Adicional> adicionais = adicionalRepository.findAllByFarmacia(farmacia);
            adicionais.forEach(adicional -> adicional.setFarmacia(null));
            farmacia.setListaAdicionais(adicionais);
            return farmacia;
        }
        return null;
    }

    public Patrocinador logarPatrocinador(String usuario, String senha) {
        Patrocinador patrocinador = patrocinadorRepository.findTopByUsuarioAcessoAndSenhaAcesso(usuario, senha);

        if (patrocinador != null) {
            List<Avaliacao> avaliacoesTotal = avaliacaoRepository.findAllByPatrocinador(patrocinador);
            Double totalSoma = avaliacoesTotal.stream().mapToDouble(a -> a.getValor()).sum();
            Double media = totalSoma / avaliacoesTotal.size();
            patrocinador.setMedia(Math.round(media / 0.5) * 0.5);

            List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByPatrocinadorAndComentarioNotNullAndRespostaIsNull(patrocinador);
            avaliacoes.forEach(avaliacao -> avaliacao.setPatrocinador(null));
            patrocinador.setAvaliacoes(avaliacoes);

            List<Contato> contatos = contatoRepository.findAllByPatrocinador(patrocinador);
            contatos.forEach(contato -> contato.setPatrocinador(null));
            patrocinador.setContatos(contatos);

            Endereco endereco = enderecoRepository.findTopByPatrocinador(patrocinador);
            endereco.setPatrocinador(null);
            patrocinador.setEndereco(endereco);

            List<Adicional> adicionais = adicionalRepository.findAllByPatrocinador(patrocinador);
            adicionais.forEach(adicional -> adicional.setPatrocinador(null));
            patrocinador.setListaAdicionais(adicionais);
            return patrocinador;
        }
        return null;
    }
}
