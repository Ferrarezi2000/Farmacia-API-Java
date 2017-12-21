package br.com.farmacia.builder;

import br.com.farmacia.dto.AvaliacaoDTO;
import br.com.farmacia.model.Avaliacao;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.AvaliacaoRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoBuild {

    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;

    public Avaliacao build(Avaliacao avaliacao, AvaliacaoDTO dto) {

        if (dto.getFarmaciaId() != null) {
            Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
            avaliacao.setFarmacia(farmacia);
        } else {
            Patrocinador patrocinador = patrocinadorRepository.findOne(dto.getPatrocinadorId());
            avaliacao.setPatrocinador(patrocinador);
        }

        avaliacao.setUsuarioNome(dto.getUsuarioNome());
        avaliacao.setEmailFacebook(dto.getEmailFacebook());
        avaliacao.setFacebookId(dto.getFacebookId());
        avaliacao.setValor(dto.getValor());
        avaliacao.setComentario(dto.getComentario());
        avaliacao.setMomento(dto.getMomento());
        avaliacao.setUsuarioImagem(dto.getUsuarioImagem());
        avaliacaoRepository.save(avaliacao);

        return avaliacao;
    }
}
