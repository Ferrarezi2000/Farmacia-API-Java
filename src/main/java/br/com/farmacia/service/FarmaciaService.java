package br.com.farmacia.service;

import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.model.*;
import br.com.farmacia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaService {

    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private FarmaciaRepository farmaciaRepository;
    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired private FuncionamentoRepository funcionamentoRepository;

    public List<Farmacia> findAllMedia() {
        List<Farmacia> farmacias = farmaciaRepository.findAll();
        farmacias.forEach(farmacia -> {
            List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByFarmaciaOrderByValorDesc(farmacia);
            Double totalSoma = avaliacoes.stream().mapToDouble(a -> a.getValor()).sum();
            Double media = totalSoma / avaliacoes.size();
            farmacia.setMedia(Math.round(media / 0.5) * 0.5);
            farmacia.setTotalAvaliacoes(avaliacoes.size());
        });
        return farmacias;
    }

    public FormCompletoDTO completo(Farmacia farmacia) {

        FormCompletoDTO completoDTO = new FormCompletoDTO();
        completoDTO.setFarmaciaId(farmacia.getId());
        completoDTO.setFarmaciaId(farmacia.getId());
        completoDTO.setFarmaciaAtivo(farmacia.getAtivo());
        completoDTO.setFarmaciaLocalidade(farmacia.getLocalidade());
        completoDTO.setFarmaciaNome(farmacia.getNome());
        completoDTO.setFarmaciaValorMensal(farmacia.getValorMensal());
        completoDTO.setFarmaciaVip(farmacia.getVip());
        completoDTO.setFarmaciaTexto(farmacia.getTexto());
        completoDTO.setFarmaciaUsuarioAcesso(farmacia.getUsuarioAcesso());
        completoDTO.setFarmaciaSenhaAcesso(farmacia.getSenhaAcesso());
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

        List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByFarmaciaOrderByValorDesc(farmacia);
        avaliacoes.forEach(avaliacao -> avaliacao.setFarmacia(null));


        Double totalSoma = avaliacoes.stream().mapToDouble(a -> a.getValor()).sum();
        completoDTO.setFarmaciaTotalAvaliacoes(avaliacoes.size());
        Double media = totalSoma / avaliacoes.size();

        completoDTO.setFarmaciaMediaAvaliacao(Math.round(media / 0.5) * 0.5);
        completoDTO.setFarmaciaTotalAvaliacoes5(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(5)).count()));
        completoDTO.setFarmaciaTotalAvaliacoes4(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(4)).count()));
        completoDTO.setFarmaciaTotalAvaliacoes3(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(3)).count()));
        completoDTO.setFarmaciaTotalAvaliacoes2(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(2)).count()));
        completoDTO.setFarmaciaTotalAvaliacoes1(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(1)).count()));
        completoDTO.setFarmaciaAvaliacoes(avaliacoes);

        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAllByFarmacia(farmacia);
        formaPagamentos.forEach(formaPagamento -> formaPagamento.setFarmacia(null));
        completoDTO.setFarmaciaPagamentos(formaPagamentos);

        List<Funcionamento> funcionamentos = funcionamentoRepository.findAllByFarmacia(farmacia);
        funcionamentos.forEach(funcionamento -> funcionamento.setFarmacia(null));
        completoDTO.setFarmaciaFuncionamentos(funcionamentos);

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
