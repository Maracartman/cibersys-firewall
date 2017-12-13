package com.cibersys.firewall.autorization.Services.RequestHandlers.GroupRequestService;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.GroupRequestDTO;
import org.springframework.http.ResponseEntity;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
public interface GroupRequestService extends AbstractPrivateRequestHandlerService
<ResponseEntity,GroupRequestDTO>{
}
