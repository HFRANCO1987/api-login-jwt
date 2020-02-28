package br.com.techsti;

import java.util.TimeZone;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		return application.sources(ApiLoginApplication.class);
	}

}
