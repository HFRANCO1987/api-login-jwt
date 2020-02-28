package br.com.techsti.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.techsti.email.EmailConfig;
import br.com.techsti.excecao.NegocioException;
import br.com.techsti.util.ResponseData;
import br.com.techsti.util.UtilMessage;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 04:08:33
 *
 * @param <E>
 */
public abstract class GenericoService<E> {

	@Autowired
	protected UtilMessage utilMessage;
	
	@Autowired
	protected EmailConfig email;
	
	protected void dispararMensagemExcecaoNegocio(String mensagem) throws NegocioException{
		throw new NegocioException(new ResponseData(HttpStatus.BAD_REQUEST.value(), mensagem));
	}
	
	protected void dispararMensagemExcecaoNegocio(ResponseData data) throws NegocioException{
		data.setStatus(HttpStatus.BAD_REQUEST.value());
		throw new NegocioException(data);
	}
	
	protected void validarDadosObrigatorios(E entidade) throws NegocioException{
		throw new NegocioException("Método validarCamposObrigatorios não implementando!");
	}
}
