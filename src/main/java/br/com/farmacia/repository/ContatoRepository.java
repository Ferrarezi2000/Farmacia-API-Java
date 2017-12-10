package br.com.farmacia.repository;

import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Contato findTopByPatrocinador(Patrocinador patrocinador);
    List<Contato> findAllByFarmacia(Farmacia farmacia);
    List<Contato> findAllByPatrocinador(Patrocinador patrocinador);
}
