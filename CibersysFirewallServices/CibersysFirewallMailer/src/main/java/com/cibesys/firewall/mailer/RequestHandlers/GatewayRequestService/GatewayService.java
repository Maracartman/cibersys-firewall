package com.cibesys.firewall.mailer.RequestHandlers.GatewayRequestService;


import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPasswordChangeRequestResponse;
import com.cibesys.firewall.mailer.Configuration.ConfigurationProperties;
import com.cibesys.firewall.mailer.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Luis Maracara on 6/16/2017.
 */
@RestController
@RequestMapping("${cibersys.services.mailer.path}")
public class GatewayService {

    @Autowired
    @Qualifier("requestHandlerCollection")
    private Map<String, Class<?>> requestHandlerCollection;

    @Autowired
    private ApplicationContext context;


    @RequestMapping(value = "/**", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity proceedAuthRequest(HttpServletRequest request, @RequestBody Map<String, String> body) {

        String serviceRequested = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
        if (requestHandlerCollection.get(serviceRequested) != null) {
            AbstractRequestHandler handler = (AbstractRequestHandler<ResponseEntity<?>>) context.getBean((Class<?>)
                    requestHandlerCollection.get(serviceRequested));
            return (ResponseEntity<?>) handler.proceedRequest(body);

        } else {
            return ResponseEntity.ok(new NewPasswordChangeRequestResponse(Long.valueOf(400),
                    "El servicio requerido no esta disponible", true, null));
        }
    }
}
