package com.cibersys.firewall.domain.models.DTO.responseDTO;

import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import lombok.Data;

/**
 * Created by AKDESK04 on 3/29/2017.
 */
@Data
public class ResponseError extends AbstractResponseBody<UserDTO>{

    public ResponseError(){}
    public ResponseError(Long code, String message, Boolean error, UserDTO response) {
        super(code, message, error, response);
    }

}
