package com.cibersys.firewall.domain.models.DTO.RequestDTO;

import com.cibersys.firewall.domain.models.DTO.model.ClientDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserInfoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

/**
 * Created by Maracartman on 31/7/2017.
 */
@AllArgsConstructor
public class NewPanelClientRequestDTO {

    private ClientDTO clientInfo;

    private UserInfoDTO userInfo;

    private String action;

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("clientInfo")
    public ClientDTO getClientInfo() {
        return clientInfo;
    }

    @JsonProperty("userInfo")
    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public NewPanelClientRequestDTO() {
    }
}
