package br.com.farmacia.builder.cadastro;

import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.*;
import br.com.farmacia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormFarmaciaBuildCompleto {

    @Autowired private AdministradorRepository administradorRepository;
    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;

    public void build(FormCompletoDTO dto) {
        Farmacia farmacia = new Farmacia();
        if (dto.getFarmaciaId() != null) {
            farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());
            farmacia.setId(dto.getFarmaciaId());
        }
        if (dto.getFarmaciaAdministradorId() != null) {
            Administrador administrador = administradorRepository.findOne(dto.getFarmaciaAdministradorId());
            farmacia.setAdministrador(administrador);
        }
        farmacia.setAtivo(dto.getFarmaciaAtivo());
        farmacia.setValorMensal(dto.getFarmaciaValorMensal());
        farmacia.setNome(dto.getFarmaciaNome());
        farmacia.setLocalidade(dto.getFarmaciaLocalidade());
        farmacia.setVip(dto.getFarmaciaVip());
        farmacia.setTexto(dto.getFarmaciaTexto());
        farmacia.setAdicionais(dto.getFarmaciaAdicionais());
        farmacia.setTituloAdicionais(dto.getFarmaciaTituloAdicionais());
        farmacia.setTextoAdicionais(dto.getFarmaciaTextoAdicionais());
        farmacia.setDelivery(dto.getFarmaciaDelivery());
        farmacia.setCodigoImagem(dto.getFarmaciaNome() + '.' + dto.getFarmaciaLocalidade());
        if (farmacia.getId() == null) {
            farmacia.setAcesso(0);
        } else {
            farmacia.setAcesso(dto.getFarmaciaAcesso());
        }
        farmaciaRepository.save(farmacia);


        Endereco endereco = new Endereco();
        if (dto.getEnderecoId() != null){
            endereco = enderecoRepository.findOne(dto.getEnderecoId());
            endereco.setId(dto.getEnderecoId());
        }
        endereco.setFarmacia(farmacia);
        endereco.setBairro(dto.getEnderecoBairro());
        endereco.setLogradouro(dto.getEnderecoLogradouro());
        endereco.setNumero(dto.getEnderecoNumero());
        enderecoRepository.save(endereco);

        Contato contato = new Contato();
        if (dto.getContatoId() != null){
            contato = contatoRepository.findOne(dto.getContatoId());
            contato.setId(dto.getContatoId());
        }
        contato.setFarmacia(farmacia);
        contato.setNumero(dto.getContatoTexto());
        contato.setTipo(dto.getContatoTipo());
        contatoRepository.save(contato);

    }

}
