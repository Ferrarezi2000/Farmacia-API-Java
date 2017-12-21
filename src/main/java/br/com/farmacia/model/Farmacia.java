package br.com.farmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(catalog = "farmacia", name = "farmacia")
@EqualsAndHashCode
public class Farmacia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String localidade;

    @NotNull
    private String codigoImagem;

    private String texto;

    private Boolean ativo;

    private Boolean vip;

    private BigDecimal valorMensal;

    private Integer acesso;

    private String senhaAcesso;

    private String usuarioAcesso;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_administrador_comissao")
    private Administrador administrador;

    @Transient
    private List<Contato> contatos;

    @Transient
    private List<Funcionamento> funcionamentos;

    @Transient
    private List<FormaPagamento> pagamentos;

    @Transient
    private Endereco endereco;

    @Transient
    private Double media;

    @Transient
    private List<Avaliacao> avaliacoes;

    @Transient
    private Integer totalAvaliacoes;

    @Transient
    private Integer totalAvaliacoes5;

    @Transient
    private Integer totalAvaliacoes4;

    @Transient
    private Integer totalAvaliacoes3;

    @Transient
    private Integer totalAvaliacoes2;

    @Transient
    private Integer totalAvaliacoes1;

}
