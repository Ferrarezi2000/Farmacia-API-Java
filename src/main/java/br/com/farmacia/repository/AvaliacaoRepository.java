package br.com.farmacia.repository;

import br.com.farmacia.model.Avaliacao;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findAllByFarmaciaOrderByValorDesc(Farmacia farmacia);
    List<Avaliacao> findAllByFarmaciaAndComentarioNotNullAndRespostaIsNull(Farmacia farmacia);
    List<Avaliacao> findAllByFarmacia(Farmacia farmacia);

    List<Avaliacao> findAllByPatrocinadorOrderByValorDesc(Patrocinador patrocinador);
    List<Avaliacao> findAllByPatrocinadorAndComentarioNotNullAndRespostaIsNull(Patrocinador patrocinador);
    List<Avaliacao> findAllByPatrocinador(Patrocinador patrocinador);

}
