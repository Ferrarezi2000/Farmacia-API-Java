package br.com.farmacia.model;

import com.sun.istack.internal.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(catalog = "farmacia", name = "avaliacao")
@EqualsAndHashCode
public class Avaliacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String usuarioNome;

//    @NotEmpty
//    private String usuarioSobrenome;

    private String usuarioImagem;

    private String emailFacebook;

    private String facebookId;

    @ManyToOne
    @JoinColumn(name = "id_farmacia")
    private Farmacia farmacia;

    @ManyToOne
    @JoinColumn(name = "id_patrocinador")
    private Patrocinador patrocinador;

    @NotNull
    private Integer valor;

    private String comentario;

    @Temporal(TemporalType.DATE)
    private Date momento;

    private String resposta;

}
