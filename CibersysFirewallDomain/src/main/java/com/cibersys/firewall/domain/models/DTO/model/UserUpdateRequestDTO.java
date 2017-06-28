package com.cibersys.firewall.domain.models.DTO.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by AKDESK25 on 6/19/2017.
 */
public class UserUpdateRequestDTO {


    private String email;

    private String verificationCode;

    private String newPassword;

    public UserUpdateRequestDTO(){
    }

    public UserUpdateRequestDTO(String email, String verificationCode, String newPassword) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.newPassword = newPassword;
    }
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
    @JsonProperty("verificationCode")
    public String getVerificationCode() {
        return verificationCode;
    }
    @JsonProperty("password")
    public String getNewPassword() {
        return newPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
