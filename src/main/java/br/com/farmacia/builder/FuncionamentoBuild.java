package br.com.farmacia.builder;

import br.com.farmacia.dto.FuncionamentoDTO;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Funcionamento;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.FuncionamentoRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FuncionamentoBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private FuncionamentoRepository funcionamentoRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;

    public Funcionamento build(Funcionamento funcionamento, FuncionamentoDTO dto) {

        if (dto.getFarmaciaId() != null) {
            Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
            funcionamento.setFarmacia(farmacia);
        } else {
            Patrocinador patrocinador = patrocinadorRepository.findOne(dto.getPatrocinadorId());
            funcionamento.setPatrocinador(patrocinador);
        }

        funcionamento.setInicio(dto.getInicio());
        funcionamento.setFim(dto.getFim());
        funcionamento.setDiaSemama(dto.getDiaSemama());
        funcionamentoRepository.save(funcionamento);

        return funcionamento;
    }
}
