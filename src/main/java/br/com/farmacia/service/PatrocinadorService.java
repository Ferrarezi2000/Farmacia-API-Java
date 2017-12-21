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
public class PatrocinadorService {

    @Autowired private PatrocinadorRepository repository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private ContatoRepository contatoRepository;
    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired private FuncionamentoRepository funcionamentoRepository;

    public List<Patrocinador> findAllMedia() {
        List<Patrocinador> patrocinadores = repository.findAll();
        patrocinadores.forEach(patrocinador -> {
            List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByPatrocinadorOrderByValorDesc(patrocinador);
            Double totalSoma = avaliacoes.stream().mapToDouble(a -> a.getValor()).sum();
            Double media = totalSoma / avaliacoes.size();
            patrocinador.setMedia(Math.round(media / 0.5) * 0.5);
            patrocinador.setTotalAvaliacoes(avaliacoes.size());
        });
        return patrocinadores;
    }

    public Patrocinador logar(PatrocinadorDTO dto) {
        Patrocinador patrocinador = repository.findTopByUsuarioAcessoAndSenhaAcesso(dto.getUsuarioAcesso(), dto.getSenhaAcesso());
        Assert.notNull(patrocinador, "Acesso negado!");
        return patrocinador;
    }

    public FormCompletoDTO completo(Patrocinador patrocinador) {

        FormCompletoDTO completoDTO = new FormCompletoDTO();
        completoDTO.setPatrocinadorId(patrocinador.getId());
        completoDTO.setPatrocinadorAtivo(patrocinador.getAtivo());
        completoDTO.setPatrocinadorNome(patrocinador.getNome());
        completoDTO.setPatrocinadorTexto(patrocinador.getTexto());
        completoDTO.setPatrocinadorValorMensal(patrocinador.getValorMensal());
        if (patrocinador.getAdministrador() != null) {
            completoDTO.setPatrocinadorAdministradorId(patrocinador.getAdministrador().getId());
        }

        Endereco endereco = enderecoRepository.findTopByPatrocinador(patrocinador);
        completoDTO.setEnderecoId(endereco.getId());
        completoDTO.setEnderecoAtivo(endereco.getAtivo());
        completoDTO.setEnderecoBairro(endereco.getBairro());
        completoDTO.setEnderecoPatrocinadorId(patrocinador.getId());
        completoDTO.setEnderecoLogradouro(endereco.getLogradouro());
        completoDTO.setEnderecoNumero(endereco.getNumero());

        List<Contato> contatos = contatoRepository.findAllByPatrocinador(patrocinador);
        contatos.forEach(contato -> {
            contato.setPatrocinador(null);
            contato.setFarmacia(null);
        });
        completoDTO.setContatos(contatos);

        List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByPatrocinadorOrderByValorDesc(patrocinador);
        avaliacoes.forEach(avaliacao -> avaliacao.setFarmacia(null));

        Double totalSoma = avaliacoes.stream().mapToDouble(a -> a.getValor()).sum();
        completoDTO.setPatrocinadorTotalAvaliacoes(avaliacoes.size());
        Double media = totalSoma / avaliacoes.size();

        completoDTO.setPatrocinadorMediaAvaliacao(Math.round(media / 0.5) * 0.5);
        completoDTO.setPatrocinadorTotalAvaliacoes5(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(5)).count()));
        completoDTO.setPatrocinadorTotalAvaliacoes4(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(4)).count()));
        completoDTO.setPatrocinadorTotalAvaliacoes3(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(3)).count()));
        completoDTO.setPatrocinadorTotalAvaliacoes2(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(2)).count()));
        completoDTO.setPatrocinadorTotalAvaliacoes1(Math.toIntExact(avaliacoes.stream().filter(a -> a.getValor().equals(1)).count()));
        completoDTO.setPatrocinadorAvaliacoes(avaliacoes);

        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAllByPatrocinador(patrocinador);
        formaPagamentos.forEach(formaPagamento -> formaPagamento.setPatrocinador(null));
        completoDTO.setPatrocinadorPagamentos(formaPagamentos);

        List<Funcionamento> funcionamentos = funcionamentoRepository.findAllByPatrocinador(patrocinador);
        funcionamentos.forEach(funcionamento -> funcionamento.setPatrocinador(null));
        completoDTO.setPatrocinadorFuncionamentos(funcionamentos);

        return completoDTO;
    }

    public void setarAcesso(Patrocinador patrocinador) {
        patrocinador.setAcesso(patrocinador.getAcesso() + 1);
        repository.save(patrocinador);
    }

    public List<Patrocinador> findAll(){
        List<Patrocinador> patrocinadores = repository.findAllByAtivoIsTrue();
        patrocinadores.forEach(patrocinador -> {
            Endereco endereco = enderecoRepository.findTopByPatrocinador(patrocinador);
            endereco.setPatrocinador(null);
            endereco.setFarmacia(null);
            patrocinador.setEndereco(endereco);

            List<Contato> contatos = contatoRepository.findAllByPatrocinador(patrocinador);
            contatos.forEach(contato -> {
                contato.setPatrocinador(null);
                contato.setFarmacia(null);
            });
            patrocinador.setContatos(contatos);
            patrocinador.setAdministrador(null);

        });

        return patrocinadores;
    }

}
