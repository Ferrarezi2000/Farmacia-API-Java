package br.com.farmacia.builder.cadastro;

import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.*;
import br.com.farmacia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormPatrocinadorBuildCompleto {

    @Autowired private AdministradorRepository administradorRepository;
    @Autowired private PatrocinadorRepository patrocinadorRepository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;

    public void build(FormCompletoDTO dto) {
        Patrocinador patrocinador = new Patrocinador();
        if (dto.getPatrocinadorId() != null) {
            patrocinador = patrocinadorRepository.findOne(dto.getPatrocinadorId());
            patrocinador.setId(dto.getPatrocinadorId());
        }
        if (dto.getPatrocinadorAdministradorId() != null) {
            Administrador administrador = administradorRepository.findOne(dto.getPatrocinadorAdministradorId());
            patrocinador.setAdministrador(administrador);
        }
        patrocinador.setAtivo(dto.getPatrocinadorAtivo());
        patrocinador.setValorMensal(dto.getPatrocinadorValorMensal());
        patrocinador.setNome(dto.getPatrocinadorNome());
        patrocinador.setAtivo(dto.getPatrocinadorAtivo());
        patrocinador.setTexto(dto.getPatrocinadorTexto());
//        patrocinador.setHoraAbrir(dto.getPatrocinadorHoraAbrir());
//        patrocinador.setHoraFechar(dto.getPatrocinadorHoraFechar());
        if (patrocinador.getId() == null) {
            patrocinador.setAcesso(0);
        } else {
            patrocinador.setAcesso(dto.getPatrocinadorAcesso());
        }
        patrocinador.setAvaliacao(dto.getPatrocinadorAvaliacao());
        patrocinadorRepository.save(patrocinador);


        Endereco endereco = new Endereco();
        if (dto.getEnderecoId() != null){
            endereco = enderecoRepository.findOne(dto.getEnderecoId());
            endereco.setId(dto.getEnderecoId());
        }
        endereco.setPatrocinador(patrocinador);
        endereco.setAtivo(dto.getEnderecoAtivo());
        endereco.setBairro(dto.getEnderecoBairro());
        endereco.setLogradouro(dto.getEnderecoLogradouro());
        endereco.setNumero(dto.getEnderecoNumero());
        enderecoRepository.save(endereco);

        Contato contato = new Contato();
        if (dto.getContatoId() != null){
            contato = contatoRepository.findOne(dto.getContatoId());
            contato.setId(dto.getContatoId());
        }
        contato.setAtivo(dto.getContatoAtivo());
        contato.setPatrocinador(patrocinador);
        contato.setTexto(dto.getContatoTexto());
        contato.setTipo(dto.getContatoTipo());
        contatoRepository.save(contato);

    }

}
