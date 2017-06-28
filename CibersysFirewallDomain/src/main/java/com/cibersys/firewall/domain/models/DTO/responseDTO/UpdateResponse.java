package com.cibersys.firewall.domain.models.DTO.responseDTO;

import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.UserUpdateResponseDTO;
import lombok.Data;

/**
 * Created by AKDESK25 on 6/19/2017.
 */
@Data
public class UpdateResponse extends AbstractResponseBody<UserUpdateResponseDTO> {

    public UpdateResponse(){}

    public UpdateResponse(Long code, String message, Boolean error, UserUpdateResponseDTO response) {
        super(code, message, error, response);
    }
}
