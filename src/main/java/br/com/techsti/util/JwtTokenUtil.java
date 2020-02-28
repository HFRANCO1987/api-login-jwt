package br.com.techsti.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.techsti.seguranca.UsuarioSistema;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 28/11/2018 - 00:45:01
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 4395281597048239750L;

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String getNomeUsuarioDoToken(String token) {
		String username;
		try {
			final Claims claims = getExtrairInformacoesDoToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public Date getDataExpiracaoDoToken(String token) {
		Date expiracao;
		try {
			final Claims claims = getExtrairInformacoesDoToken(token);
			expiracao = claims.getExpiration();
		} catch (Exception e) {
			expiracao = null;
		}
		return expiracao;
	}

	public Claims getExtrairInformacoesDoToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public Boolean isTokenExpirado(String token) {
		final Date expiracao = getDataExpiracaoDoToken(token);
		return expiracao.before(new Date());
	}
	
	public String gerarToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		
		final Date dataCriacao = new Date();
		claims.put(CLAIM_KEY_CREATED, dataCriacao);
		
		return generateBuilderToken(claims);
	}

	private String generateBuilderToken(Map<String, Object> claims) {
		final Date dataCriacao = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date dataExpiracao = new Date(dataCriacao.getTime() + expiration * 1000);
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public Boolean isTokenPodeSerAtualizado(String token) {
		return (!isTokenExpirado(token));
	}

	public String atualizarToken(String token) {
		String atToken;
		try {
			final Claims claims = getExtrairInformacoesDoToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			atToken = generateBuilderToken(claims);
		}catch (Exception e) {
			atToken = null;
		}
		return atToken;
	}
	
	
	public Boolean validarSeTokenEhValido(String token, UserDetails userDetails) {
		UsuarioSistema user = (UsuarioSistema) userDetails;
		final String userName = getNomeUsuarioDoToken(token);
		return userName.equals(user.getUsername()) && !isTokenExpirado(token);
	}
}
