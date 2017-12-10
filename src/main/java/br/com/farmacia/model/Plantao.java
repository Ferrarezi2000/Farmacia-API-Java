package br.com.farmacia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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
    private Integer dia;

    @NotEmpty
    private String mes;

    @NotNull
    private Integer ano;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

}
