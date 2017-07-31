package com.cibersys.firewall.RequestHandlers.SetClienteRequestService;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;

/**
 * Created by Maracartman on 31/7/2017.
 */
public interface SetClienteService extends
        AbstractPrivateRequestHandlerService<AbstractResponseBody,NewPanelClientRequestDTO> {

}
