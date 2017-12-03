package br.com.farmacia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(catalog = "farmacia", name = "plantao")
@EqualsAndHashCode
public class Plantao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_farmacia")
    private Farmacia farmacia;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_calendario")
    private Calendario calendario;

    @Transient private Endereco endereco;


}
