package br.com.zupacademy.natacha.mercadolivre.repository;

import br.com.zupacademy.natacha.mercadolivre.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriaRepository extends CrudRepository<Usuario, Long> {

}
