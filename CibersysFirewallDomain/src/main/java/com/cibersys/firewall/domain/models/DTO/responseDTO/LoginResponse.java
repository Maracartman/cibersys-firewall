package com.cibersys.firewall.domain.models.DTO.responseDTO;

import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import lombok.Data;

/**
 * Created by AKDESK25 on 6/14/2017.
 */
@Data
public class LoginResponse extends AbstractResponseBody<UserDTO> {

    public LoginResponse(){}

    public LoginResponse(Long code, String message, Boolean error, UserDTO response) {
        super(code, message, error, response);
    }
}
