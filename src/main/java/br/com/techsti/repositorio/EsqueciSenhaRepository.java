package br.com.techsti.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.techsti.email.EsqueciSenha;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 14:33:14
 *
 */
public interface EsqueciSenhaRepository extends CrudRepository<EsqueciSenha, Long> {

	EsqueciSenha findByInscricao(String inscricao);

}
