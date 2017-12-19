package br.com.farmacia.service;

import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Patrocinador;
import br.com.farmacia.repository.FarmaciaRepository;
import br.com.farmacia.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class LogarService {

    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;

    public Farmacia logarFarmacia(String usuario, String senha) {
        Farmacia farmacia = farmaciaRepository.findTopByUsuarioAcessoAndSenhaAcesso(usuario, senha);
        return farmacia;
    }

    public Patrocinador logarPatrocinador(String usuario, String senha) {
        Patrocinador patrocinador = patrocinadorRepository.findTopByUsuarioAcessoAndSenhaAcesso(usuario, senha);
        return patrocinador;
    }
}
