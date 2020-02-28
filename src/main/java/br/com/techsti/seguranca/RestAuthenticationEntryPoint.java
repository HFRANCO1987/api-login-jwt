package br.com.techsti.seguranca;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.techsti.excecao.NegocioException;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 01:30:11
 *
 */
@ControllerAdvice
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 01:27:45
	 *
	 * RN: 401, Unauthorized -> Erro de autenticação 
	 *
	 * @param request
	 * @param response
	 * @param accessDeniedException
	 * @throws IOException
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// 401
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Falha de Autenticação");
	}

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 01:26:22
	 *
	 * RN: 403, Forbidden -> Autenticação bem sucedida, mas sem privilégios suficientes.
	 *
	 * @param request
	 * @param response
	 * @param accessDeniedException
	 * @throws IOException
	 */
	@ExceptionHandler(value = { AccessDeniedException.class })
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {
		// 403
		response.sendError(HttpServletResponse.SC_FORBIDDEN,
				"Authorization Failed : " + accessDeniedException.getMessage());
	}

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 01:25:44
	 *
	 * RN: 500, Internal Server error -> Algum erro inesperado na aplicação.
	 * Ex: um problema da infraestrutura
	 *
	 * @param request
	 * @param response
	 * @param exception
	 * @throws IOException
	 */
	@ExceptionHandler(value = { Exception.class })
	public void commence(HttpServletRequest request, HttpServletResponse response, Exception exception)
			throws IOException {
		// 500
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
	}

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 01:24:49
	 *
	 * RN: 400, Bad Request -> Recurso foi acessado, porém ocorreu algum erro na validação
	 * de alguma regra de negócio da aplicação
	 *
	 * @param req
	 * @param e
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(value = { NegocioException.class })
	public void catchBussinessException(HttpServletRequest req, final NegocioException e, HttpServletResponse response)
			throws IOException {
		if (e.getResponseData() != null) {
			response.sendError(e.getResponseData().getStatus(), e.getResponseData().getMessage());
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}

}
