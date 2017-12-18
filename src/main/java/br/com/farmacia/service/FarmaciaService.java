package br.com.farmacia.service;

import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.Contato;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.repository.ContatoRepository;
import br.com.farmacia.repository.EnderecoRepository;
import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaService {

    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private FarmaciaRepository farmaciaRepository;

    public FormCompletoDTO completo(Farmacia farmacia) {

        FormCompletoDTO completoDTO = new FormCompletoDTO();
        completoDTO.setFarmaciaId(farmacia.getId());
        completoDTO.setFarmaciaAtivo(farmacia.getAtivo());
        completoDTO.setFarmaciaLocalidade(farmacia.getLocalidade());
        completoDTO.setFarmaciaNome(farmacia.getNome());
        completoDTO.setFarmaciaValorMensal(farmacia.getValorMensal());
        completoDTO.setFarmaciaHoraAbrir(farmacia.getHoraAbrir());
        completoDTO.setFarmaciaHoraFechar(farmacia.getHoraFechar());
        completoDTO.setFarmaciaVip(farmacia.getVip());
        completoDTO.setFarmaciaTexto(farmacia.getTexto());
        if (farmacia.getAdministrador() != null) {
            completoDTO.setFarmaciaAdministradorId(farmacia.getAdministrador().getId());
        }

        Endereco endereco = enderecoRepository.findTopByFarmacia(farmacia);
        completoDTO.setEnderecoId(endereco.getId());
        completoDTO.setEnderecoAtivo(endereco.getAtivo());
        completoDTO.setEnderecoBairro(endereco.getBairro());
        completoDTO.setEnderecoFarmaciaId(farmacia.getId());
        completoDTO.setEnderecoLogradouro(endereco.getLogradouro());
        completoDTO.setEnderecoNumero(endereco.getNumero());


        List<Contato> contatos = contatoRepository.findAllByFarmacia(farmacia);
        contatos.forEach(contato -> {
            contato.setFarmacia(null);
            contato.setPatrocinador(null);
        });
        completoDTO.setContatos(contatos);

        return completoDTO;
    }

    public void setarAcesso(Farmacia farmacia) {
        farmacia.setAcesso(farmacia.getAcesso() + 1);
        farmaciaRepository.save(farmacia);
    }

    public List<Farmacia> addEndeContato() {
        List<Farmacia> farmacias = farmaciaRepository.findAllByVipIsTrue();
        farmacias.forEach(farmacia -> {
            List<Contato> contatos = contatoRepository.findAllByFarmacia(farmacia);
            contatos.forEach(contato -> {
                contato.setPatrocinador(null);
                contato.setFarmacia(null);
            });
            farmacia.setContatos(contatos);

            Endereco endereco = enderecoRepository.findTopByFarmacia(farmacia);
            endereco.setPatrocinador(null);
            endereco.setFarmacia(null);
            farmacia.setEndereco(endereco);
        });
        return farmacias;
    }

}
