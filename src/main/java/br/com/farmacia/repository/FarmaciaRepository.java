package br.com.farmacia.repository;

import br.com.farmacia.model.Administrador;
import br.com.farmacia.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {

    List<Farmacia> findAllByVipIsTrue();

    List<Farmacia> findAllByAdministrador(Administrador administrador);

}
