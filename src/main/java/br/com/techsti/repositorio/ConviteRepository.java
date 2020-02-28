package br.com.techsti.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.techsti.entidade.Convite;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 12:49:14
 *
 */
public interface ConviteRepository extends CrudRepository<Convite, Long> {

	@Query("SELECT c FROM Convite c WHERE c.inscricao = :inscricao")
	Convite findByInscricao(@Param("inscricao") String inscricao);

}
