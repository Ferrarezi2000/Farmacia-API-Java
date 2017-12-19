package br.com.farmacia.repository;

import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Funcionamento;
import br.com.farmacia.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionamentoRepository extends JpaRepository<Funcionamento, Long> {

    List<Funcionamento> findAllByFarmacia(Farmacia farmacia);
    List<Funcionamento> findAllByPatrocinador(Patrocinador patrocinador);

}
