package com.cibersys.firewall.domain.models.DTO.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Luis Maracara on 6/19/2017.
 */
public class UserUpdateResponseDTO {
    private String email;

    private String newPassword;

    public UserUpdateResponseDTO(){}


    public UserUpdateResponseDTO(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("password")
    public String getNewPassword() {
        return newPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
