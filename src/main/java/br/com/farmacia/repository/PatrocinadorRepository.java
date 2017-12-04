package br.com.farmacia.repository;

import br.com.farmacia.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Long> {

    List<Patrocinador> findAllByAtivoIsTrue();

}
