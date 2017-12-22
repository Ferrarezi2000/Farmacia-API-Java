package br.com.farmacia.repository;

import br.com.farmacia.model.Adicional;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdicionalRepository extends JpaRepository<Adicional, Long> {

    List<Adicional> findAllByFarmacia(Farmacia farmacia);
    List<Adicional> findAllByPatrocinador(Patrocinador patrocinador);

}
