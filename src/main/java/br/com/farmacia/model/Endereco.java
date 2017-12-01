package br.com.farmacia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(catalog = "farmacia", name = "endereco")
@EqualsAndHashCode
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String lougradouro;

    @NotEmpty
    private String bairro;

    @NotEmpty
    private String numero;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_farmacia")
    private Farmacia farmacia;

    private Boolean ativo;

}
