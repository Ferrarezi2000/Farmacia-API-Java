package br.com.farmacia.repository;

import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Contato findTopByFarmacia(Farmacia farmacia);
    Contato findTopByPatrocinador(Patrocinador patrocinador);
}
