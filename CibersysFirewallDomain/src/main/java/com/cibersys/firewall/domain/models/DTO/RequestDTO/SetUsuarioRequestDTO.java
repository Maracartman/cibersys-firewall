package com.cibersys.firewall.domain.models.DTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Maracartman on 27/7/2017.
 */
@Data
@AllArgsConstructor
public class SetUsuarioRequestDTO {

    protected String action;
    protected String name;
    protected String lastName;
    protected String email;
    protected Boolean block;


    @JsonProperty("block")
    public Boolean getBlock() {
        return block;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public SetUsuarioRequestDTO() {
    }
}
