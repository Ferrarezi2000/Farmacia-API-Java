package br.com.farmacia.service;

import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatrocinadorService {

    @Autowired private PatrocinadorRepository repository;

}
