package com.cibesys.firewall.mailer.Factory.Parameters;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Luis Maracara on 6/22/2017.
 */
@Data
@AllArgsConstructor
public class MailSenderParameters {
    protected String request,username,token,job,email,name,surName,type,password,confirmPassword,img;
}
