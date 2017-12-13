package com.cibersys.firewall.groups.RequestHandlers.GatewayRequestService;


import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.groups.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/16/2017.
 */
@RestController
@RequestMapping("${cibersys.services.group.path}")
public class GatewayService {

    @Autowired
    @Qualifier("requestHandlerCollection")
    private Map<String, Class<?>> requestHandlerCollection;

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/**", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AbstractResponseBody proceedAuthRequest(HttpServletRequest request, @RequestBody Map<String,String> body,
                                                   @RequestHeader Map<String,String> header){

        String serviceRequested = request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1];
        if(requestHandlerCollection.get(serviceRequested) != null){
            AbstractRequestHandler handler  = (AbstractRequestHandler<UserDTO>) context.getBean((Class<?>)
                    requestHandlerCollection.get(serviceRequested));
            return (AbstractResponseBody) handler.proceedRequest(body,header);
        }else{
            return null;
        }
    }

}
