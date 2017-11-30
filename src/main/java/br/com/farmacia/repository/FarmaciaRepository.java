package br.com.farmacia.repository;

import br.com.farmacia.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {

}
