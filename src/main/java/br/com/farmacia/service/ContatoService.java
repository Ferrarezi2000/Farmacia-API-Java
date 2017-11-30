package br.com.farmacia.service;

import br.com.farmacia.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    @Autowired private ContatoRepository repository;

}
