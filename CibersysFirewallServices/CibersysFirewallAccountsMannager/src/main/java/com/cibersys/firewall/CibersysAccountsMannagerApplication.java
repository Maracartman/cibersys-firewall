package com.cibersys.firewall;

import com.cibersys.firewall.RequestHandlers.LoginRequestService.LoginService;
import com.cibersys.firewall.RequestHandlers.PasswordChange.PasswordChange;
import com.cibersys.firewall.RequestHandlers.PasswordChangeRequestService.PasswordChangeRequest;
import com.cibersys.firewall.RequestHandlers.SetClienteRequestService.SetClienteService;
import com.cibersys.firewall.RequestHandlers.SetUsuarioService.SetUsuarioService;
import com.cibersys.firewall.RequestHandlers.UserUpdateRequestService.UserUpdateRequestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CibersysAccountsMannagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CibersysAccountsMannagerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public Map<String,Class<?>> requestHandlerCollection(){
		Map<String, Class<?>> map = new HashMap<String,Class<?>>();

		/**
		 *
		 * Aqui se agregaran todos los controladores que se encargaran de recibir el request
		 * y construir el response.
		 *
		 **/
		map.put("usuario", LoginService.class);
		map.put("update", UserUpdateRequestService.class);
		map.put("recuperatepassword", PasswordChangeRequest.class);
		map.put("passwordchange", PasswordChange.class);
		map.put("passwordchange", PasswordChange.class);
		map.put("setUsuario", SetUsuarioService.class);
		map.put("setCliente", SetClienteService.class);

		return map;
	}
}
