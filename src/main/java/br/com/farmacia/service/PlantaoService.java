package br.com.farmacia.service;

import br.com.farmacia.dto.BuscarPlantoesDTO;
import br.com.farmacia.dto.PlantaoCelDTO;
import br.com.farmacia.model.*;
import br.com.farmacia.repository.ContatoRepository;
import br.com.farmacia.repository.EnderecoRepository;
import br.com.farmacia.repository.PlantaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlantaoService {

    @Autowired private PlantaoRepository repository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private MesService mesService;

    public List<PlantaoCelDTO> plantoesMes(BuscarPlantoesDTO dto){

        String mes = mesService.data(dto.getData());

        List<Plantao> plantoes = repository.findAllByMesAndAnoOrderByDia(mes, new GregorianCalendar().get(Calendar.YEAR));

        List<PlantaoCelDTO> plantaoCelDTOS = new ArrayList<>();
        plantoes.forEach(plantao -> {
            PlantaoCelDTO celDTO = new PlantaoCelDTO();
            celDTO.setDia(plantao.getDia());
            celDTO.setLocalidade(plantao.getFarmacia().getLocalidade());
            celDTO.setNomeFarmacia(plantao.getFarmacia().getNome());
            plantaoCelDTOS.add(celDTO);
        });

        return plantaoCelDTOS;
    }

    public List<Plantao> plantaosDia(BuscarPlantoesDTO dto) {
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(dto.getData());
        dto.setMes(mesService.data(dto.getData()));

        List<Plantao> plantoes = repository.findAllByDiaAndMesAndAno(dataCal.get(Calendar.DATE),
                dto.getMes(), dataCal.get(Calendar.YEAR));

        plantoes.forEach(p -> {
            List<Contato> contatos = contatoRepository.findAllByFarmacia(p.getFarmacia());
            contatos.forEach(contato -> contato.setFarmacia(null));
            p.getFarmacia().setContatos(contatos);

            Endereco endereco = enderecoRepository.findTopByFarmacia(p.getFarmacia());
            endereco.setFarmacia(null);
            p.getFarmacia().setEndereco(endereco);

        });

        return plantoes;
    }
}
