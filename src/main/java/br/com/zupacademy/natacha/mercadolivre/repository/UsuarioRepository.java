package br.com.zupacademy.natacha.mercadolivre.repository;

import br.com.zupacademy.natacha.mercadolivre.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
