package br.com.techsti.util;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 01:02:13
 *
 */
public class Utilitarios {

	@Autowired
	private MessageSource messageSource;
	
	public String getMessageKey(String key) {
		return messageSource.getMessage(key, null, Locale.getDefault());
	}
	
	/**
	 * Valida se objeto esta nulo ou vazio
	 */
	public static Boolean isPreenchimentoNuloOuVazio(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof CharSequence) {
			return (((CharSequence) obj).length() == 0);
		} else if (obj instanceof Number) {
			return (((Number) obj).longValue() == 0);
		} else if (obj instanceof Collection<?>) {
			return (((Collection<?>) obj).isEmpty());
		} else if (obj instanceof Map) {
			return (((Map<?, ?>) obj).isEmpty());
		} else if (obj instanceof Object[]) {
			return (((Object[]) obj).length == 0);
		}
		return false;
	}

	/** Adicionar dias em uma data
	 * @param int dias - Quantos dias deve adicionar a data
	 * @param Date data - Data Inicial para a soma
	 * @return Date data - Nova data adicionados os dias
	 * */
	public static Date adicionarDias(Date data, Long dias) {
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(data);
		
		calendar.add(Calendar.DATE,dias.intValue());  
		return calendar.getTime();  
	}
	
	/**
	 * 
	 * @author DF-DEV-2 henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 20:56:14
	 *
	 * RN: Verifica se a dataAtual é maior que a data futura 
	 *
	 * @param dataA
	 * @param dataB
	 * @return
	 */
	public static boolean dataMaior(Date dataAtual, Date dataFutura) {
		return dataAtual.after(dataFutura);
	}
	
	
	/**
	 * Converte string para base64
	 * @param atributo
	 * @return
	 */
	public static String encondeToString(String atributo) {
		return Base64.getEncoder().encodeToString(atributo.getBytes()); 
	}

	
	/**
	 * Decodifica string base64
	 * @param atributo
	 * @return
	 */
	public static String decodeString(String atributo) {
		byte[] decodedBytes = Base64.getDecoder().decode(atributo.getBytes());
		return new String(decodedBytes);
	}

	/**
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 20:15:16
	 *
	 * RN: Gera um número aleatório para inscrição
	 *
	 * @return
	 */
	public static String gerarNumeroInscricao() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
		Random random = new Random();
		String armazenaChaves = "";
		int index = -1;
		for (int i = 0; i < 5; i++) {
			index = random.nextInt(letras.length());
			armazenaChaves += letras.substring(index, index + 1);
		}
		return armazenaChaves.toUpperCase();
	}

	public static String gerarIdentificadorInscricao() {
		UUID uuid = UUID.randomUUID();
		long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
		return Long.toString(l, Character.MAX_RADIX).toString().concat("-API-").concat(gerarNumeroInscricao());
	}
}
