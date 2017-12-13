package com.cibesys.firewall.mailer;


import com.cibesys.firewall.mailer.RequestHandlers.NewPasswordRequestService.NewPasswordRequestService;
import com.cibesys.firewall.mailer.RequestHandlers.PasswordChangeRequestService.PasswordChangeRequest;
import com.cibesys.firewall.mailer.RequestHandlers.SetUsuarioRequestService.SetUsuarioService;
import com.cibesys.firewall.mailer.Senders.NewPasswordSender.NewPasswordSender;
import com.cibesys.firewall.mailer.Senders.RecuperaMailSender.RecuperateMailSenderService;
import com.cibesys.firewall.mailer.Senders.SetUsuarioMailSender.SetUsuarioMailSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CibersysFirewallMailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CibersysFirewallMailerApplication.class, args);
	}

	@Bean
	public Map<String,Class<?>> requestHandlerCollection(){
		Map<String, Class<?>> map = new HashMap<>();
		/**
		 * Aqui se agregaran todos los controladores que se encargaran de recibir el request
		 * y construir el response
		 *
		 * **/
		map.put("recuperatepassword", PasswordChangeRequest.class);
		map.put("update", NewPasswordRequestService.class);
		map.put("setUsuario", SetUsuarioService.class);
		return map;
	}

	@Bean
	public Map<String,Class<?>> requestMailerCollection(){
		Map<String, Class<?>> map = new HashMap<>();
		/**
		 * Aqui se agregaran todos los controladores que se encargaran de recibir el request
		 * y construir el response
		 *
		 * **/
		map.put("recuperatepassword",RecuperateMailSenderService.class);
		map.put("update",NewPasswordSender.class);
		map.put("setUsuario",SetUsuarioMailSender.class);
		return map;
	}
}
