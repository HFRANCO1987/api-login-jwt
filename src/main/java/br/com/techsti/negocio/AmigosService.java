package br.com.techsti.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.techsti.entidade.Amigos;
import br.com.techsti.repositorio.AmigosRepository;

/**
 * 
 * @author Amigos henriquesantiagofranco@gmail.com
 * @date 28/11/2018 - 17:16:16
 *
 */
@Service
public class AmigosService extends GenericoService<Amigos> implements Serializable {

	private static final long serialVersionUID = 1595826595664693340L;

	@Autowired
	private AmigosRepository repository;

	public void desfazerAmizade(Amigos amigo) {
		Optional<Amigos> objAmigo = this.repository.findById(amigo.getId());
		if (objAmigo.isPresent()) {
			objAmigo.get().setAtivo(Boolean.FALSE);
			this.repository.save(objAmigo.get());
		} else {
			dispararMensagemExcecaoNegocio("Não foi possível desfazer relacionamento de amizade!");
		}
	}

	public List<Amigos> findByPorIdConvidante(Long idConvidante) {
		List<Amigos> listaAmigos = this.repository.findByPorIdConvidante(idConvidante); 
		return listaAmigos == null ? new ArrayList<>() : listaAmigos;
	}

}
