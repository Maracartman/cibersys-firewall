package com.cibersys.firewall.domain.models.DTO.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Maracartman on 1/8/2017.
 */
@AllArgsConstructor
@Data
public class UserInfoDTO {

    private String userName, password;
    private Integer idRol;
    private String name, lastName;
    private Boolean edited_mail;
    private Long userId;
    private Boolean blocked;

    @JsonProperty("userId")
    public Long getUserId() {
        return userId;
    }

    @JsonProperty("edited_mail")
    public Boolean getEdited_mail() {
        return edited_mail;
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
    public String getUserName() {
        return userName;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("rol")
    public Integer getIdRol() {
        return idRol;
    }

    @JsonProperty("block")
    public Boolean getBlocked() {
        return blocked;
    }
}
