package com.cibersys.firewall.autorization;

import com.cibersys.firewall.autorization.Services.RequestHandlers.LoginRequestService.LoginService;
import com.cibersys.firewall.autorization.Services.RequestHandlers.PasswordChangeRequest.PasswordChangeRequest;
import com.cibersys.firewall.autorization.Services.RequestHandlers.UserUpdateRequestService.UserUpdateRequestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKDESK25 on 6/13/2017.
 */
@SpringBootApplication
public class AuthorizationController {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationController.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    @Bean
    public Map<String,Class<?>> requestHandlerCollection(){
        Map<String, Class<?>> map = new HashMap<String, Class<?>>();

        /**
         * Aqui se agregaran todos los controladores que se encargaran de recibir el request
         * y construir el response
         *
         * **/
        map.put("public/login", LoginService.class);
        map.put("public/update", UserUpdateRequestService.class);
        map.put("public/recuperatepassword", PasswordChangeRequest.class);


        return map;
    }
}
