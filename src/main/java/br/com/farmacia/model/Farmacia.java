package br.com.farmacia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    private Boolean ativo;

    private Boolean vip;

    private BigDecimal valorMensal;

    @ManyToOne
    @JoinColumn(name = "id_administrador_comissao")
    private Administrador administrador;

    @Transient
    private List<Contato> contatos;
    @Transient
    private Endereco endereco;

}
