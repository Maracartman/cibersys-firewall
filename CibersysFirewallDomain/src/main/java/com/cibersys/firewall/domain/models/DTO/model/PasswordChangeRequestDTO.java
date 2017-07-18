package com.cibersys.firewall.domain.models.DTO.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Maracartman on 18/7/2017.
 */
public class PasswordChangeRequestDTO {




    private String email;

    private String oldPassword;

    private String newPassword;

    public PasswordChangeRequestDTO(){
    }

    public PasswordChangeRequestDTO(String email, String verificationCode, String newPassword) {
        this.email = email;
        this.oldPassword = verificationCode;
        this.newPassword = newPassword;
    }
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
    @JsonProperty("oldPassword")
    public String getOldPassword() {
        return oldPassword;
    }
    @JsonProperty("password")
    public String getNewPassword() {
        return newPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}
