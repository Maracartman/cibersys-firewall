package com.cibersys.firewall.autorization.Services.RequestHandlers.SetClienteRequestService;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import org.springframework.http.ResponseEntity;

/**
 * Created by Maracartman on 31/7/2017.
 */
public interface SetClienteService extends
        AbstractPrivateRequestHandlerService<ResponseEntity,NewPanelClientRequestDTO>{

}
