package br.com.farmacia.builder;

import br.com.farmacia.dto.FormaPagamentoDTO;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.FormaPagamento;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.FormaPagamentoRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;

    public FormaPagamento build(FormaPagamento formaPagamento, FormaPagamentoDTO dto) {

        if (dto.getFarmaciaId() != null) {
            Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
            formaPagamento.setFarmacia(farmacia);
        } else {
            Patrocinador patrocinador = patrocinadorRepository.findOne(dto.getPatrocinadorId());
            formaPagamento.setPatrocinador(patrocinador);
        }

        formaPagamento.setTipo(dto.getTipo());
        formaPagamentoRepository.save(formaPagamento);

        return formaPagamento;
    }
}
