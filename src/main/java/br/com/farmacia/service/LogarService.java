package br.com.farmacia.service;

import br.com.farmacia.model.Avaliacao;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.AvaliacaoRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogarService {

    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;
    @Autowired private AvaliacaoRepository avaliacaoRepository;

    public Farmacia logarFarmacia(String usuario, String senha) {
        Farmacia farmacia = farmaciaRepository.findTopByUsuarioAcessoAndSenhaAcesso(usuario, senha);

        List<Avaliacao> avaliacoesTotal = avaliacaoRepository.findAllByFarmacia(farmacia);
        Double totalSoma = avaliacoesTotal.stream().mapToDouble(a -> a.getValor()).sum();
        Double media = totalSoma / avaliacoesTotal.size();
        farmacia.setMedia(Math.round(media / 0.5) * 0.5);

        List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByFarmaciaAndComentarioNotNullAndRespostaIsNull(farmacia);
        avaliacoes.forEach(avaliacao -> avaliacao.setFarmacia(null));
        farmacia.setAvaliacoes(avaliacoes);
        farmacia.setAdministrador(null);
        return farmacia;
    }

    public Patrocinador logarPatrocinador(String usuario, String senha) {
        Patrocinador patrocinador = patrocinadorRepository.findTopByUsuarioAcessoAndSenhaAcesso(usuario, senha);

        List<Avaliacao> avaliacoesTotal = avaliacaoRepository.findAllByPatrocinador(patrocinador);
        Double totalSoma = avaliacoesTotal.stream().mapToDouble(a -> a.getValor()).sum();
        Double media = totalSoma / avaliacoesTotal.size();
        patrocinador.setMedia(Math.round(media / 0.5) * 0.5);

        List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByPatrocinadorAndComentarioNotNullAndRespostaIsNull(patrocinador);
        avaliacoes.forEach(avaliacao -> avaliacao.setPatrocinador(null));
        patrocinador.setAvaliacoes(avaliacoes);
        return patrocinador;
    }
}
