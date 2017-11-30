package br.com.farmacia.service;

import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmaciaService {

    @Autowired private FarmaciaRepository farmaciaRepository;

}
