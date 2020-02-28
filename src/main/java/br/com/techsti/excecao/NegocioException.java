package br.com.techsti.excecao;

import br.com.techsti.util.ResponseData;

/**
 * 
 * @author Henrique Santiago Utilizada apenas para exceções de Regra de Negócio
 *
 */
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -5514514846504913690L;

	private ResponseData responseData;

	public NegocioException(String msg) {
		super(msg);
	}

	public NegocioException(ResponseData responseData) {
		this.responseData = responseData;
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

}
