package com.cibersys.firewall.RequestHandlers.SetClienteService;

import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;

/**
 * Created by AKDESK25 on 8/1/2017.
 */
public interface SetClienteService extends
        AbstractPrivateRequestHandlerService
                <AbstractResponseBody,NewPanelClientRequestDTO> {
}
