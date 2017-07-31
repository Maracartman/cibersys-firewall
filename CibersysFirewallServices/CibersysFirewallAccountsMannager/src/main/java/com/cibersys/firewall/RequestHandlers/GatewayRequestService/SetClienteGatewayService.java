package com.cibersys.firewall.RequestHandlers.GatewayRequestService;



import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Maracartman on 31/7/2017.
 */
@Controller
@RequestMapping("${cibersys.services.path}")
public class SetClienteGatewayService {

    @Autowired
    @Qualifier("requestHandlerCollection")
    private Map<String, Class<?>> requestHandlerCollection;

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "**/setCliente", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> proceedLogin(HttpServletRequest request, HttpServletResponse response,
                                          @RequestBody NewPanelClientRequestDTO body,
                                          @RequestHeader Map<String,String> header){
        String serviceRequested = request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1];
        if(requestHandlerCollection.get(serviceRequested) != null){
            AbstractPrivateRequestHandlerService handler  =
                    (AbstractPrivateRequestHandlerService<ResponseEntity<?>,NewPanelClientRequestDTO>) context.getBean((Class<?>)
                    requestHandlerCollection.get(serviceRequested));
            return (ResponseEntity<?>) handler.proceedRequest(body,header);
        }else{
            return ResponseEntity.ok(new ResponseError(Long.valueOf(401),"Servicio no válido",false,null));
        }
    }


}
