package com.cibersys.firewall.domain.models.DTO.responseDTO;

import com.cibersys.firewall.domain.models.DTO.RequestDTO.GroupRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
public class GroupResponseDTO extends AbstractResponseBody<List<GroupRequestDTO>> {

    public GroupResponseDTO(Long code, String message, Boolean error, List<GroupRequestDTO> response) {
        super(code, message, error, response);
    }

    public GroupResponseDTO() {
    }
}
