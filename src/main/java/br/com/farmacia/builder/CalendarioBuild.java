package br.com.farmacia.builder;

import br.com.farmacia.dto.CalendarioDTO;
import br.com.farmacia.dto.ContatoDTO;
import br.com.farmacia.model.Calendario;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.repository.CalendarioRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalendarioBuild {

    public Calendario build(Calendario calendario, CalendarioDTO dto) {

        calendario.setDia(dto.getDia());
        calendario.setMes(dto.getMes());
        calendario.setAno(dto.getAno());

        return calendario;
    }
}
