package com.cibersys.firewall.groups;

import com.cibersys.firewall.groups.RequestHandlers.GroupRequestService.GroupRequestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CibersysFirewallGroupsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CibersysFirewallGroupsApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public Map<String,Class<?>> requestHandlerCollection(){
		Map<String, Class<?>> map = new HashMap<>();

		/**
		 *
		 * Aqui se agregaran todos los controladores que se encargaran de recibir el request
		 * y construir el response.
		 *
		 **/
		map.put("group", GroupRequestService.class);

		return map;
	}


}
