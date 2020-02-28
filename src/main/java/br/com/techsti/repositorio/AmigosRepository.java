package br.com.techsti.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.techsti.entidade.Amigos;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 28/11/2018 - 17:18:56
 *
 */
public interface AmigosRepository extends CrudRepository<Amigos, Long> {

	@Query("SELECT a FROM Amigos a where a.convidante.id = :idconvidante ORDER BY a.amigo.nome ASC")
	List<Amigos> findByPorIdConvidante(@Param("idconvidante") Long idConvidante);


}
