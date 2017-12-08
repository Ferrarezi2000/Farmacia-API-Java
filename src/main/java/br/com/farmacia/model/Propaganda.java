package br.com.farmacia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(catalog = "farmacia", name = "propaganda")
@EqualsAndHashCode
public class Propaganda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_farmacia")
    private Farmacia farmacia;

    @ManyToOne
    @JoinColumn(name = "id_patrocinador")
    private Patrocinador patrocinador;

    private Boolean segunda;
    private Boolean terca;
    private Boolean quarta;
    private Boolean quinta;
    private Boolean sexta;

}
