package com.cibersys.firewall.RequestHandlers.GatewayRequestService;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Maracartman on 31/7/2017.
 */
@RestController
@RequestMapping("${cibersys.services.dbm.path}")
public class SetClienteGatewayService {

    @Autowired
    @Qualifier("requestHandlerCollection")
    private Map<String, Class<?>> requestHandlerCollection;

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "**/setCliente", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AbstractResponseBody proceedLogin(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody NewPanelClientRequestDTO body,
                                             @RequestHeader Map<String,String> header){
        String serviceRequested = request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1];
        if(requestHandlerCollection.get(serviceRequested) != null){
            AbstractPrivateRequestHandlerService handler  =
                    (AbstractPrivateRequestHandlerService<ResponseEntity<?>,NewPanelClientRequestDTO>) context.getBean((Class<?>)
                    requestHandlerCollection.get(serviceRequested));
            return (AbstractResponseBody) handler.proceedRequest(body,header);
        }else{
            return new ResponseError(Long.valueOf(401),"Servicio no válido",false,null);
        }
    }


}
