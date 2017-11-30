package br.com.farmacia.service;

import br.com.farmacia.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired private EnderecoRepository repository;

}
