package br.com.farmacia.service;

import br.com.farmacia.dto.DiasDTO;
import br.com.farmacia.model.Propaganda;
import br.com.farmacia.repository.PropagandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Random;

@Service
public class PropagandaService {

    @Autowired private PropagandaRepository repository;

    public Propaganda retornarPropagandaDia(DiasDTO dto){
        List<Propaganda> propagandas = repository.findAllBySegundaOrTercaOrQuartaOrQuintaOrSextaOrSabadoOrDomingo(
                dto.getSegunda(), dto.getTerca(), dto.getQuarta(), dto.getQuinta(), dto.getSexta(), dto.getSabado(),
                dto.getDomingo());

        int i = 1 + (int)(Math.random() * propagandas.size());
        Random random = new Random(propagandas.size());
        Propaganda propaganda = propagandas.get(i-1);

        return propaganda;
    }

}
