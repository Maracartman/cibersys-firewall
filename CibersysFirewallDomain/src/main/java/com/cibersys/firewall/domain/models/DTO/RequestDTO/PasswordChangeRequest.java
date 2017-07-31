package com.cibersys.firewall.domain.models.DTO.RequestDTO;

import com.cibersys.firewall.domain.models.DTO.RequestBody.AbstractPrivateRequestBody;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import lombok.Data;

import java.util.ArrayList;

/**
 * Created by Maracartman on 25/7/2017.
 */
@Data
public class PasswordChangeRequest extends AbstractPrivateRequestBody<ArrayList<PasswordChangeRequestDTO>>{


    public PasswordChangeRequest() {
    }

    public PasswordChangeRequest(ArrayList<PasswordChangeRequestDTO> request) {
        super(request);
    }
}
