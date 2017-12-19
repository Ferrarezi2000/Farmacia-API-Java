package br.com.farmacia.service;

import br.com.farmacia.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService {

    @Autowired private FormaPagamentoRepository repository;

}
