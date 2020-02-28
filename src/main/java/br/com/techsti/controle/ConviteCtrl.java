package br.com.techsti.controle;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techsti.entidade.Convite;
import br.com.techsti.entidade.Usuario;
import br.com.techsti.negocio.ConviteService;
import br.com.techsti.negocio.UsuarioService;
import br.com.techsti.to.DadosBasicosTO;
import br.com.techsti.util.ResponseData;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 20:28:44
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/convite")
public class ConviteCtrl implements Serializable {

	private static final long serialVersionUID = -9109457839773418110L;

	@Autowired
	private ConviteService conviteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PutMapping(value = "/aceite")
	public ResponseData confirmarInscricao(@RequestBody DadosBasicosTO dados) throws Exception {
		Convite convite = this.conviteService.findByInscricao(dados.getInscricao());
		return this.conviteService.realizarAceiteDoConvite(convite);
	}
	
	@PostMapping
	public ResponseData fazerConvite(@RequestBody DadosBasicosTO dados) throws Exception {
		Usuario usuario = this.usuarioService.findByEmail(dados.getConvidante());
		this.conviteService.gerarConvite(new Convite(usuario, dados.getEmail()));
		return new ResponseData(null, "Convite enviado com sucesso!");
	}
	
}
