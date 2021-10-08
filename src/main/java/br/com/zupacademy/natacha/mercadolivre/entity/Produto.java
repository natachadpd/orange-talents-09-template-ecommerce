package br.com.zupacademy.natacha.mercadolivre.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomeProduto;

    @NotNull
    @Min(1)
    private BigDecimal valor;

    @PositiveOrZero
    @NotNull
    private Integer qtdDisponivel;

    @JoinColumn(name = "id_produto")
    @Size(min=3)
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Caracteristicas> caracteristicas;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotBlank
    @Length(max = 1000, message = "Máximo 1000 caracteres")
    private String descricao;


    private LocalDateTime cadastro = LocalDateTime.now();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nomeProduto,@NotNull @Min(1) BigDecimal valor, @PositiveOrZero @NotNull Integer qtdDisponivel,
                   @Size(min=3) Set<Caracteristicas> caracteristicas, @NotNull Categoria categoria, String descricao) {
        this.nomeProduto = nomeProduto;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.descricao = descricao;
    }
}
