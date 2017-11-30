package br.com.farmacia.repository;

import br.com.farmacia.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Administrador findTopBySenhaAndNome(String senha, String nome);

}
