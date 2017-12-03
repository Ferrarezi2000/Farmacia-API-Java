package br.com.farmacia.service;

import br.com.farmacia.dto.PreencherCalendarioDTO;
import br.com.farmacia.model.Calendario;
import br.com.farmacia.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarioService {

    @Autowired private CalendarioRepository repository;

    public void comporCalendario(PreencherCalendarioDTO dto) {

        for (int i = 1; i <= dto.getDias(); i++) {

            Calendario calendario = new Calendario();
            calendario.setDia(i);
            calendario.setMes(dto.getMes());
            calendario.setAno(2018);
            repository.save(calendario);
        }
    }
}
