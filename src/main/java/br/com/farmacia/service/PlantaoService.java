package br.com.farmacia.service;

import br.com.farmacia.dto.BuscarPlantoesDTO;
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

    public List<Plantao> plantoesMes(String mes){

        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(new Date());

        List<Plantao> plantoes = repository.findAllByMesAndAno(mes, dataCal.get(Calendar.YEAR));
        return plantoes;
    }

    public List<Plantao> plantaosDia(BuscarPlantoesDTO dto) {
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(dto.getData());

        switch (dataCal.get(Calendar.MONTH)) {
            case 0:
                dto.setMes("Janeiro");
                break;
            case 1:
                dto.setMes("Fevereiro");
                break;
            case 2:
                dto.setMes("Março");
                break;
            case 3:
                dto.setMes("Abril");
                break;
            case 4:
                dto.setMes("Maio");
                break;
            case 5:
                dto.setMes("Junho");
                break;
            case 6:
                dto.setMes("Julho");
                break;
            case 7:
                dto.setMes("Agosto");
                break;
            case 8:
                dto.setMes("Setembro");
                break;
            case 9:
                dto.setMes("Outubro");
                break;
            case 10:
                dto.setMes("Novembro");
                break;
            case 11:
                dto.setMes("Dezembro");
                break;
        }



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
