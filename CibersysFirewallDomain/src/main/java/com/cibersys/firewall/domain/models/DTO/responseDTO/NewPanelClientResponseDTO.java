package com.cibersys.firewall.domain.models.DTO.responseDTO;

import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;

import java.util.List;

/**
 * Created by Maracartman on 31/7/2017.
 */
public class NewPanelClientResponseDTO extends AbstractResponseBody<List<NewPanelClientRequestDTO>> {
    public NewPanelClientResponseDTO(Long code, String message, Boolean error, List<NewPanelClientRequestDTO> response) {
        super(code, message, error, response);
    }

    public NewPanelClientResponseDTO() {
    }
}
