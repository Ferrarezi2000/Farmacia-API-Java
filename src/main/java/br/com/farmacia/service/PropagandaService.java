package br.com.farmacia.service;

import br.com.farmacia.repository.PropagandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropagandaService {

    @Autowired private PropagandaRepository repository;

}
