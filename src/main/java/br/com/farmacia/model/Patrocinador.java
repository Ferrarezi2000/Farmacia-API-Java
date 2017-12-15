package br.com.farmacia.model;

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
@Table(catalog = "farmacia", name = "patrocinador")
@EqualsAndHashCode
public class Patrocinador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    private Boolean ativo;

    @NotNull
    private BigDecimal valorMensal;

    @NotNull
    private String horaAbrir;

    @NotNull
    private String horaFechar;

    @NotEmpty
    private String texto;

    @ManyToOne
    @JoinColumn(name = "id_administrador_comissao")
    private Administrador administrador;

    @Transient
    private Endereco endereco;

    @Transient
    private List<Contato> contatos;

}
