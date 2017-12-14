package br.com.farmacia.repository;

import br.com.farmacia.model.Plantao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantaoRepository extends JpaRepository<Plantao, Long> {

    List<Plantao> findAllByMesAndAnoOrderByDia(String mes, Integer ano);

    List<Plantao> findAllByDiaAndMesAndAno(Integer dia, String mes, Integer ano);

}
