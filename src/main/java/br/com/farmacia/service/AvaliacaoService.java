package br.com.farmacia.service;

import br.com.farmacia.dto.AvaliacaoDTO;
import br.com.farmacia.model.Avaliacao;
import br.com.farmacia.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired private AvaliacaoRepository repository;

    public void setarResposta(Avaliacao avaliacao, AvaliacaoDTO dto) {
        avaliacao.setResposta(dto.getResposta());
        repository.save(avaliacao);
    }

}
