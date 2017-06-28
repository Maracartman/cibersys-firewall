package com.cibersys.firewall.autorization.Services.RequestHandlers.GatewayRequestService;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by AKDESK25 on 6/16/2017.
 */
@Controller
@RequestMapping("${cibersys.services.path}")
public class GatewayService {

    @Autowired
    @Qualifier("requestHandlerCollection")
    private Map<String, Class<?>> requestHandlerCollection;

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/**", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> proceedAuthRequest(HttpServletRequest request, HttpServletResponse response,
                                                @RequestBody Map<String,String> body){
        String serviceRequested = request.getRequestURI().split("/")[request.getRequestURI().split("/").length-2] +"/"+
                request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1];
        if(requestHandlerCollection.get(serviceRequested) != null){
            AbstractRequestHandler handler  = (AbstractRequestHandler<ResponseEntity<?>>) context.getBean((Class<?>)
                    requestHandlerCollection.get(serviceRequested));
            return (ResponseEntity<?>) handler.proceedRequest(body);
        }else{
            return ResponseEntity.ok(new ResponseError(Long.valueOf(401),"Servicio no válido",false,null));
        }
    }
}
