package br.com.farmacia.repository;

import br.com.farmacia.model.Avaliacao;
import br.com.farmacia.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findAllByFarmaciaOrderByValorDesc(Farmacia farmacia);

}
