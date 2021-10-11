package br.com.zupacademy.natacha.mercadolivre.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto<encodedfile> {

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

    @NotNull
    @Valid
    @ManyToOne
    private Usuario dono;

    private LocalDateTime cadastro = LocalDateTime.now();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nomeProduto, @NotNull @Min(1) BigDecimal valor, @PositiveOrZero @NotNull Integer qtdDisponivel,
                   @Size(min=3) Set<Caracteristicas> caracteristicas, @NotNull Categoria categoria, String descricao, @NotNull @Valid Usuario dono) {
        this.nomeProduto = nomeProduto;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.descricao = descricao;
        this.dono = dono;

    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", valor=" + valor +
                ", qtdDisponivel=" + qtdDisponivel +
                ", caracteristicas=" + caracteristicas +
                ", categoria=" + categoria +
                ", descricao='" + descricao + '\'' +
                ", dono=" + dono +
                ", imagens=" + imagens +
                '}';
    }

    public void associaImagens(Set<String> links){
        Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }


    public boolean pertenceAoUsuario(Usuario possivelDono) {
        return this.dono.equals(possivelDono);
    }
}
