package br.com.techsti.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.techsti.entidade.Usuario;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 00:59:25
 *
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u where u.email = :email ORDER BY u.id ASC")
	Page<Usuario> findByEmail(@Param("email") String email, Pageable pageable);

}
