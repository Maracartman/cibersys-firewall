package com.cibersys.firewall.domain.models.DTO.responseDTO;


import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import lombok.Data;

import java.util.List;

/**
 * Created by Maracartman on 27/7/2017.
 */
@Data
public class SetUsuarioReponseDTO extends AbstractResponseBody<List<SetUsuarioResponse>>{
    public SetUsuarioReponseDTO(Long code, String message, Boolean error,List<SetUsuarioResponse> response) {
        super(code, message, error, response);
    }

    public SetUsuarioReponseDTO() {
    }
}
