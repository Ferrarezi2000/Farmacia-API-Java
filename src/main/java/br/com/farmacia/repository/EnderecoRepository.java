package br.com.farmacia.repository;

import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findAllByFarmacia(Farmacia farmacia);

}
