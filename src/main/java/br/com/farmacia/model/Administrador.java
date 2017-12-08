package br.com.farmacia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(catalog = "farmacia", name = "administrador")
@EqualsAndHashCode
public class Administrador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String token;

    private Boolean ativo;

    @Transient
    private List<Farmacia> farmacias = new ArrayList<>();

    @Transient
    private List<Patrocinador> patrocinadores = new ArrayList<>();

    @Transient
    private Double totalValorFarmacia = 0.0;

    @Transient
    private Double totalValorPatrocinador = 0.0;

}
