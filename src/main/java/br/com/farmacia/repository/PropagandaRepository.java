package br.com.farmacia.repository;

import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.model.Propaganda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropagandaRepository extends JpaRepository<Propaganda, Long> {

    List<Propaganda> findAllBySegundaOrTercaOrQuartaOrQuintaOrSextaOrSabadoOrDomingo(Boolean segunda,
                                                                                     Boolean terca,
                                                                                     Boolean quarta,
                                                                                     Boolean quinta,
                                                                                     Boolean sexta,
                                                                                     Boolean sabado,
                                                                                     Boolean domingo);
}
