package com.cibersys.firewall.autorization;

import com.cibersys.firewall.autorization.Services.RequestHandlers.CountryService.CountryService;
import com.cibersys.firewall.autorization.Services.RequestHandlers.GroupRequestService.GroupRequestService;
import com.cibersys.firewall.autorization.Services.RequestHandlers.LoginRequestService.LoginService;
import com.cibersys.firewall.autorization.Services.RequestHandlers.PasswordChangeService.PasswordChange;
import com.cibersys.firewall.autorization.Services.RequestHandlers.PasswordChangeRequestService.PasswordChangeRequest;
import com.cibersys.firewall.autorization.Services.RequestHandlers.UserUpdateRequestService.UserUpdateRequestService;
import com.cibersys.firewall.autorization.Services.RequestHandlers.SetUsuarioService.SetUsuarioService;
import com.cibersys.firewall.autorization.Services.RequestHandlers.SetClienteRequestService.SetClienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Luis Maracara on 6/13/2017.
 */
@SpringBootApplication
public class AuthorizationController {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationController.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("*").allowCredentials(true).allowedHeaders("*");

            }
        };
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
        map.put("public/countries", CountryService.class);
        map.put("private/passwordchange", PasswordChange.class);
        map.put("private/user", SetUsuarioService.class);
        map.put("private/client", SetClienteService.class);
        map.put("private/group", GroupRequestService.class);



        return map;
    }
}
