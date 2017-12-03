package br.com.farmacia.service;

import br.com.farmacia.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarioService {

    @Autowired private CalendarioRepository repository;

}
