package com.cibesys.firewall.mailer.RequestHandlers.PasswordChangeRequestService.impl;

import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPasswordChangeRequestResponse;
import com.cibesys.firewall.mailer.Factory.MailSenderFactory;
import com.cibesys.firewall.mailer.Factory.Parameters.MailSenderParameters;
import com.cibesys.firewall.mailer.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibesys.firewall.mailer.RequestHandlers.PasswordChangeRequestService.PasswordChangeRequest;
import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/21/2017.
 */
@Service
public class PasswordChangeRequestImpl extends AbstractRequestHandler<ResponseEntity<?>> implements PasswordChangeRequest {

    @Autowired
    private MailSenderFactory mailSenderFactory;


    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body) {
        NewPasswordChangeRequestDTO newPasswordRequest = objectMapper.convertValue(body,NewPasswordChangeRequestDTO.class);
        AbstractEmailSenderService sender = mailSenderFactory.constructSender(
                new MailSenderParameters("recuperatepassword",
                        newPasswordRequest.getEmail(),newPasswordRequest.getValidationCode(),newPasswordRequest.getRol(),newPasswordRequest.getEmail(),newPasswordRequest.getNombre(),
                        newPasswordRequest.getApellido(),"password","password",null,newPasswordRequest.getImagen_url()));
        try {
            sender.sendEmail();
            return ResponseEntity.ok(new NewPasswordChangeRequestResponse(Long.valueOf(200),
                    "Correo enviado correctamente",false,null));
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.ok(new NewPasswordChangeRequestResponse(Long.valueOf(200),
                    "Los datos no son válidos",true,null));
        }
    }
}
