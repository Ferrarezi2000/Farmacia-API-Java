package br.com.farmacia.service;

import br.com.farmacia.model.*;
import br.com.farmacia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaAPPService {

    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired private FuncionamentoRepository funcionamentoRepository;

    public Farmacia build(Farmacia farmacia) {

        Endereco endereco = enderecoRepository.findTopByFarmacia(farmacia);
        endereco.setFarmacia(null);
        farmacia.setEndereco(endereco);


        List<Contato> contatos = contatoRepository.findAllByFarmacia(farmacia);
        contatos.forEach(contato -> contato.setFarmacia(null));
        farmacia.setContatos(contatos);

        List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByFarmaciaOrderByValorDesc(farmacia);
        avaliacoes.forEach(avaliacao -> avaliacao.setFarmacia(null));
        farmacia.setAvaliacoes(avaliacoes);


        Double totalSoma = avaliacoes.stream().mapToDouble(a -> a.getValor()).sum();
        farmacia.setTotalAvaliacoes(avaliacoes.size());
        Double media = totalSoma / avaliacoes.size();

        farmacia.setMedia(Math.round(media / 0.5) * 0.5);
        farmacia.setTotalAvaliacoes5(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(5)).count()));
        farmacia.setTotalAvaliacoes4(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(4)).count()));
        farmacia.setTotalAvaliacoes3(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(3)).count()));
        farmacia.setTotalAvaliacoes2(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(2)).count()));
        farmacia.setTotalAvaliacoes1(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(1)).count()));

        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAllByFarmacia(farmacia);
        formaPagamentos.forEach(formaPagamento -> formaPagamento.setFarmacia(null));
        farmacia.setPagamentos(formaPagamentos);

        List<Funcionamento> funcionamentos = funcionamentoRepository.findAllByFarmacia(farmacia);
        funcionamentos.forEach(funcionamento -> funcionamento.setFarmacia(null));
        farmacia.setFuncionamentos(funcionamentos);

//        if (farmacia.getAdicionais()) {
//            farmacia.setListaAdicionais(adicionalRepository.findAllByFarmacia(farmacia));
//        }

        return farmacia;
    }
}
