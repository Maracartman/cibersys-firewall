package com.cibersys.firewall.domain.models.DTO.RequestDTO;

import com.cibersys.firewall.domain.models.DTO.model.ClientDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

/**
 * Created by Maracartman on 31/7/2017.
 */
@AllArgsConstructor
public class NewPanelClientRequestDTO {

    private ClientDTO clientInfo;

    private UserDTO userInfo;

    @JsonProperty("clientInfo")
    public ClientDTO getClientInfo() {
        return clientInfo;
    }
    @JsonProperty("userInfo")
    public UserDTO getUserInfo() {
        return userInfo;
    }

    public NewPanelClientRequestDTO() {
    }
}
