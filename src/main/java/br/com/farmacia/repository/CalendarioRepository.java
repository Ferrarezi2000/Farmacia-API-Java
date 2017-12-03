package br.com.farmacia.repository;

import br.com.farmacia.model.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

    Calendario findTopByDiaAndMes(Integer dia, String mes);

}
