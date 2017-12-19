package br.com.farmacia.service;

import br.com.farmacia.repository.FuncionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionamentoService {

    @Autowired private FuncionamentoRepository repository;

}
