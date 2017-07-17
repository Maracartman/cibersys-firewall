package com.cibersys.firewall.RequestHandlers.LoginRequestService;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractRequestHandlerService;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.LoginResponse;
import org.springframework.http.ResponseEntity;

/**
 * Created by Luis Maracara on 6/16/2017.
 */
public interface LoginService extends AbstractRequestHandlerService<LoginResponse> {
}
