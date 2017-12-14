package br.com.farmacia.service;

import br.com.farmacia.dto.DiasDTO;
import br.com.farmacia.model.Propaganda;
import br.com.farmacia.repository.PropagandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropagandaService {

    @Autowired private PropagandaRepository repository;

    public Propaganda retornarPropagandaDia(DiasDTO dto){
        List<Propaganda> propagandas = repository.findAllBySegundaOrTercaOrQuartaOrQuintaOrSextaOrSabadoOrDomingo(
                dto.getSegunda(), dto.getTerca(), dto.getQuarta(), dto.getQuinta(), dto.getSexta(), dto.getSabado(),
                dto.getDomingo());

        int i = 1 + (int)(Math.random() * propagandas.size());
        Propaganda propaganda = propagandas.get(i-1);

        return propaganda;
    }

}
