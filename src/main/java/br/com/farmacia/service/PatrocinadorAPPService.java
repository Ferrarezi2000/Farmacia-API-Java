package br.com.farmacia.service;

import br.com.farmacia.dto.FormCompletoDTO;
import br.com.farmacia.dto.PatrocinadorDTO;
import br.com.farmacia.model.*;
import br.com.farmacia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PatrocinadorAPPService {

    @Autowired private PatrocinadorRepository repository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired private FuncionamentoRepository funcionamentoRepository;

    public Patrocinador build(Patrocinador patrocinador) {

        Endereco endereco = enderecoRepository.findTopByPatrocinador(patrocinador);
        endereco.setPatrocinador(null);
        patrocinador.setEndereco(endereco);
        List<Contato> contatos = contatoRepository.findAllByPatrocinador(patrocinador);
        contatos.forEach(contato -> {
            contato.setPatrocinador(null);
            contato.setFarmacia(null);
        });
        patrocinador.setContatos(contatos);

        List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByPatrocinadorOrderByValorDesc(patrocinador);
        avaliacoes.forEach(avaliacao -> avaliacao.setPatrocinador(null));
        patrocinador.setAvaliacoes(avaliacoes);


        Double totalSoma = avaliacoes.stream().mapToDouble(a -> a.getValor()).sum();
        patrocinador.setTotalAvaliacoes(avaliacoes.size());
        Double media = totalSoma / avaliacoes.size();

        patrocinador.setMedia(Math.round(media / 0.5) * 0.5);
        patrocinador.setTotalAvaliacoes5(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(5)).count()));
        patrocinador.setTotalAvaliacoes4(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(4)).count()));
        patrocinador.setTotalAvaliacoes3(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(3)).count()));
        patrocinador.setTotalAvaliacoes2(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(2)).count()));
        patrocinador.setTotalAvaliacoes1(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(1)).count()));

        List<Funcionamento> funcionamentos = funcionamentoRepository.findAllByPatrocinador(patrocinador);
        funcionamentos.forEach(funcionamento -> funcionamento.setPatrocinador(null));
        patrocinador.setFuncionamentos(funcionamentos);

        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAllByPatrocinador(patrocinador);
        formaPagamentos.forEach(formaPagamento -> formaPagamento.setPatrocinador(null));
        patrocinador.setPagamentos(formaPagamentos);

        return patrocinador;
    }

}
