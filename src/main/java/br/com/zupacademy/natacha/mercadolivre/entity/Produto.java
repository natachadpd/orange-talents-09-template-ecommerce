package br.com.zupacademy.natacha.mercadolivre.entity;

import br.com.zupacademy.natacha.mercadolivre.controller.dto.Opinioes;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto{


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

    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();;


    @Deprecated
    public Produto() {
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Produto(@NotBlank String nomeProduto, @NotNull @Min(1) BigDecimal valor,
                   @PositiveOrZero @NotNull Integer qtdDisponivel,
                   @Size(min=3) Set<Caracteristicas> caracteristicas,
                   @NotNull Categoria categoria, String descricao, @NotNull @Valid Usuario dono) {
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

    public BigDecimal getValor() {
        return valor;
    }

    public Set<Caracteristicas> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public <T> Set<T>  mapeiaCaracteristicas(Function<Caracteristicas, T> funcaoMapeadora){
        return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T>  mapeiaImagens(Function<ImagemProduto, T> funcaoMapeadora){
        return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T extends  Comparable<T>> SortedSet<T>  mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora){
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }


    public boolean temEstoque(@Min(1) Integer qtdDisponivel){
        return this.qtdDisponivel >= qtdDisponivel;
    }


    public  boolean estoqueAtualiza(@Positive Integer qtdDisponivel){
        Assert.isTrue(qtdDisponivel > 0, "A quantidade deve ser maior que zero para abater o estoque "+qtdDisponivel);
        if(temEstoque (qtdDisponivel)){
            this.qtdDisponivel-=qtdDisponivel;
            return true;
        }

        return false;
    }

}

