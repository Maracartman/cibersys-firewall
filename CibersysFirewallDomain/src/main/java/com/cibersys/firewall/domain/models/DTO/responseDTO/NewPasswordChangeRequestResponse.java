package com.cibersys.firewall.domain.models.DTO.responseDTO;

import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPasswordChangeRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Luis Maracara on 6/21/2017.
 */
@Data
@AllArgsConstructor
public class NewPasswordChangeRequestResponse extends AbstractResponseBody<NewPasswordChangeRequestDTO>{
    public NewPasswordChangeRequestResponse(Long code, String message, Boolean error, NewPasswordChangeRequestDTO response) {
        super(code, message, error, response);
    }
}
