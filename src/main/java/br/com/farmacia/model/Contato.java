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
@Table(catalog = "farmacia", name = "contato")
@EqualsAndHashCode
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String tipo;

    @NotEmpty
    private String texto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_farmacia")
    private Farmacia farmacia;

    private Boolean ativo = true;

}
