package br.com.farmacia.repository;

import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Endereco findTopByFarmacia(Farmacia farmacia);
    Endereco findTopByPatrocinador(Patrocinador patrocinador);

}
