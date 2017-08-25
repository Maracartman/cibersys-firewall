package com.cibersys.firewall.RequestHandlers.GatewayRequestService;


/**
 * Created by AKDESK25 on 8/25/2017.
 */


import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.GroupRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${cibersys.services.dbm.services}")
public class GroupGatewayService {

    @Autowired
    @Qualifier("requestHandlerCollection")
    private Map<String, Class<?>> requestHandlerCollection;

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "**/group", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AbstractResponseBody proceedLogin(HttpServletRequest request,
                                          @RequestBody GroupRequestDTO body,
                                          @RequestHeader Map<String,String> header){
        String serviceRequested = request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1];
        if(requestHandlerCollection.get(serviceRequested) != null){
            AbstractPrivateRequestHandlerService handler  =
                    (AbstractPrivateRequestHandlerService<ResponseEntity<?>,GroupRequestDTO>) context.getBean((Class<?>)
                            requestHandlerCollection.get(serviceRequested));
            return (AbstractResponseBody) handler.proceedRequest(body,header);
        }else{
            return null;
        }
    }


}
