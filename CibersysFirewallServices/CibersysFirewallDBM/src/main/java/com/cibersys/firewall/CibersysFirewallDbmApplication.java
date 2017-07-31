package com.cibersys.firewall;


import com.cibersys.firewall.RequestHandlers.PasswordChangeRequestService.PasswordChangeRequestService;
import com.cibersys.firewall.RequestHandlers.PasswordChangeService.PasswordChangeService;
import com.cibersys.firewall.RequestHandlers.SetUsuarioService.SetUsuarioService;
import com.cibersys.firewall.RequestHandlers.UserUpdateRequestService.UserUpdateRequestService;
import com.cibersys.firewall.RequestHandlers.UserVerification.UserVerification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CibersysFirewallDbmApplication {
    public static void main(String[] args) {
        SpringApplication.run(CibersysFirewallDbmApplication.class, args);
    }

    @Bean
    public Map<String, Class<?>> requestHandlerCollection() {
        Map<String, Class<?>> map = new HashMap<>();

        /**
         * Aqui se agregaran todos los controladores que se encargaran de recibir el request
         * y construir el response
         *
         * **/
        map.put("usuario", UserVerification.class);
        map.put("update", UserUpdateRequestService.class);
        map.put("recuperatepassword", PasswordChangeRequestService.class);
        map.put("passwordchange", PasswordChangeService.class);
        map.put("setUsuario", SetUsuarioService.class);


        return map;
    }

}
