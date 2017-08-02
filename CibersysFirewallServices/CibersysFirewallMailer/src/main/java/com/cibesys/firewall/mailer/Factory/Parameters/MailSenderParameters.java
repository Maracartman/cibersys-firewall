package com.cibesys.firewall.mailer.Factory.Parameters;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Luis Maracara on 6/22/2017.
 */
@Data
@AllArgsConstructor
public class MailSenderParameters {

    public MailSenderParameters(String request, String name, String email, String surName, String password) {
        this.request = request;
        this.name = name;
        this.email = email;
        this.surName = surName;
        this.password = password;
    }

    protected String request,username,token,job,email,name,surName,type,password,confirmPassword,img;
}
