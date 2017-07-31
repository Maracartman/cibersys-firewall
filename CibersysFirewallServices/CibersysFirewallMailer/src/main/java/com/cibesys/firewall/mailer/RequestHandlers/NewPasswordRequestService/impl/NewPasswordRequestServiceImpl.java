package com.cibesys.firewall.mailer.RequestHandlers.NewPasswordRequestService.impl;

import com.cibersys.firewall.domain.models.DTO.RequestDTO.UserUpdateRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPasswordChangeRequestResponse;
import com.cibesys.firewall.mailer.Factory.MailSenderFactory;
import com.cibesys.firewall.mailer.Factory.Parameters.MailSenderParameters;
import com.cibesys.firewall.mailer.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibesys.firewall.mailer.RequestHandlers.NewPasswordRequestService.NewPasswordRequestService;
import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/26/2017.
 */
@Service
public class NewPasswordRequestServiceImpl extends AbstractRequestHandler<ResponseEntity<?>> implements NewPasswordRequestService {

    @Autowired
    private MailSenderFactory mailSenderFactory;

    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body) {
        UserUpdateRequestDTO newPasswordRequest = objectMapper.convertValue(body, UserUpdateRequestDTO.class);
        AbstractEmailSenderService sender = mailSenderFactory.constructSender(
                new MailSenderParameters("update",
                        newPasswordRequest.getEmail(), null, null, newPasswordRequest.getEmail(),null,
                        null, "password", "password", null, null));
        try {
            sender.sendEmail();
            return ResponseEntity.ok(new NewPasswordChangeRequestResponse(Long.valueOf(200),
                    "Correo enviado correctamente", false, null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new NewPasswordChangeRequestResponse(Long.valueOf(200),
                    "Los datos no son válidos", true, null));
        }
    }
}
