package br.com.farmacia.service;

import br.com.farmacia.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    @Autowired private AdministradorRepository repository;

}
