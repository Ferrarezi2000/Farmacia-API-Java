package br.com.farmacia.repository;

import br.com.farmacia.model.Propaganda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropagandaRepository extends JpaRepository<Propaganda, Long> {

    List<Propaganda> findAllByDiaSemana(String diaSemana);
}
