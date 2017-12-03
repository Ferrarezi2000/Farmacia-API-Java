package br.com.farmacia.service;

import br.com.farmacia.model.Calendario;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Plantao;
import br.com.farmacia.repository.EnderecoRepository;
import br.com.farmacia.repository.PlantaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantaoService {

    @Autowired private PlantaoRepository repository;
    @Autowired private EnderecoRepository enderecoRepository;

    public List<Plantao> plantoes(Calendario calendario){
        List<Plantao> plantoes = repository.findAllByCalendario(calendario);
        plantoes.forEach(p -> {
            Endereco endereco = enderecoRepository.findTopByFarmacia(p.getFarmacia());
            endereco.setFarmacia(null);
            p.setEndereco(endereco);
        });

        return plantoes;
    }

}
