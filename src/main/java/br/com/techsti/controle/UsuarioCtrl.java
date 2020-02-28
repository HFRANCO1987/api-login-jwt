package br.com.techsti.controle;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techsti.entidade.Usuario;
import br.com.techsti.negocio.UsuarioService;
import br.com.techsti.seguranca.DadosDoUsuarioService;
import br.com.techsti.to.DadosBasicosTO;
import br.com.techsti.util.JwtTokenUtil;
import br.com.techsti.util.ResponseData;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 00:57:41
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/usuario")
public class UsuarioCtrl implements Serializable {

	private static final long serialVersionUID = -8870699623137598742L;

	@Autowired
	private DadosDoUsuarioService dadosDoUsuarioService; 
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/autenticar", consumes="application/json")
	public ResponseData autenticar(@RequestBody Usuario usuario) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails usuarioSistema = dadosDoUsuarioService.loadUserByUsername(usuario.getEmail());
		String token = jwtTokenUtil.gerarToken(usuarioSistema);
		Usuario usuarioLogado = usuarioService.findByEmail(usuario.getEmail());
		return new ResponseData(usuarioLogado, "Usuário autenticado com sucesso!", HttpStatus.OK, token);
	}

	@PostMapping(value="/novo")
	public ResponseData salvar(@RequestBody Usuario usuario) {
		this.usuarioService.gravar(usuario);
		return new ResponseData(null, "Cadastro realizado com sucesso!");
	}
	
	@PostMapping(value = "/novasenha")
	public ResponseData gerar(@RequestBody DadosBasicosTO dadosBasicos) {
		this.usuarioService.gerarNovaSenha(dadosBasicos.getSenha());
		return new ResponseData(null, "Sua senha foi alterada com sucesso, verifique seu e-mail!");
	}
	
}
