package br.com.zupacademy.natacha.mercadolivre.compra;

import br.com.zupacademy.natacha.mercadolivre.email.EmailSend;
import br.com.zupacademy.natacha.mercadolivre.produto.Produto;
import br.com.zupacademy.natacha.mercadolivre.usuario.Usuario;
import br.com.zupacademy.natacha.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/compras")
public class CompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailSend emails;


    @PostMapping
    @ResponseStatus(HttpStatus.FOUND)
    @Transactional
    public String compra(@RequestBody @Valid CompraForm compraForm,
                         Authentication authentication,
                         UriComponentsBuilder uriComponentsBuilder) throws BindException {


        Optional<Usuario> comprador = usuarioRepository.findByLogin(authentication.getName());

        if (comprador.isEmpty()) {
            throw new EntityNotFoundException("Usuário inexistente");
        }

        Produto produto = manager.find(Produto.class, compraForm.getIdProduto());
        boolean estoque = produto.estoqueAtualiza(compraForm.getQuantidade());

        if (estoque) {
            Compra novaCompra = compraForm.toCompra(produto, comprador.get());
            manager.persist(novaCompra);
            emails.novaCompra(novaCompra, produto);

            GatewayPagamento gateway = compraForm.getGateway();

            return novaCompra.urlGateway(uriComponentsBuilder);
        }

        BindException problemaComEstoque = new BindException(compraForm, "novaCompraForm");
        problemaComEstoque.reject(null,
                "Não foi possível realizar a compra por falta de estoque!");
        throw problemaComEstoque;
    }
}