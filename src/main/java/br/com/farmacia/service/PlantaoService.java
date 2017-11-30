package br.com.farmacia.service;

import br.com.farmacia.repository.PlantaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantaoService {

    @Autowired private PlantaoRepository repository;

}
