package com.cibersys.firewall.domain.models.DTO.RequestDTO;

import com.cibersys.firewall.domain.models.DTO.model.ClientDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserInfoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Maracartman on 31/7/2017.
 */
@AllArgsConstructor
@Data
public class NewPanelClientRequestDTO {

    private ClientDTO clientInfo;

    private UserInfoDTO userInfo;

    private List<UserInfoDTO> clientUsers;

    private String action;

    private Boolean block;

    public NewPanelClientRequestDTO() {
    }

    @JsonProperty("block")
    public Boolean getBlock() {
        return block;
    }

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

    @JsonProperty("clientUsers")
    public List<UserInfoDTO> getClientUsers(){return this.clientUsers;}
}
