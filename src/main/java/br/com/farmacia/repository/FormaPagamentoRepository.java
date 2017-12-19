package br.com.farmacia.repository;

import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.FormaPagamento;
import br.com.farmacia.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    List<FormaPagamento> findAllByFarmacia(Farmacia farmacia);
    List<FormaPagamento> findAllByPatrocinador(Patrocinador patrocinador);
}
