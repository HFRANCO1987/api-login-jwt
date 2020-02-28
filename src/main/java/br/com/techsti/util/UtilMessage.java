package br.com.techsti.util;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 12:30:50
 *
 */
@Component
public class UtilMessage {

	@Autowired
	private MessageSource messageSource;
	
	public String getMessageKey(String key) {
		return messageSource.getMessage(key, null, Locale.getDefault());
	}
	
}
