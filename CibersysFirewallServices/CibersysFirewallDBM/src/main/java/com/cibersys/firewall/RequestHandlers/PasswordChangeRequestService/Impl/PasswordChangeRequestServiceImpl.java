package com.cibersys.firewall.RequestHandlers.PasswordChangeRequestService.Impl;

import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.UsuarioRepository;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.PasswordChangeRequestService.PasswordChangeRequestService;
import com.cibersys.firewall.Utilities.ManagerToken;
import com.cibersys.firewall.converter.ConverterUtilities;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPasswordChangeRequestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by Luis Maracara on 6/22/2017.
 */
@Service
public class PasswordChangeRequestServiceImpl extends AbstractRequestHandler<NewPasswordChangeRequestResponse> implements PasswordChangeRequestService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ManagerToken managerToken;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConverterUtilities converter;


    @Override
    public NewPasswordChangeRequestResponse proceedRequest(Map<String, String> body,Map<String, String> header) {
        NewPasswordChangeRequestDTO request = objectMapper.convertValue(body, NewPasswordChangeRequestDTO.class);
        Usuario userRequested = usuarioRepository.findByEmailAndEstatus(request.getEmail(), "1");
        if (userRequested != null) {
            userRequested.setCodigoValidacion(managerToken.generateRandomToken());
            userRequested.setFechaCodigoValidacion(converter.getFormattedDate("yyyy-MM-dd HH:mm:ss",
                    new Date()));
            usuarioRepository.save(userRequested);
            return new NewPasswordChangeRequestResponse(Long.valueOf(200), "", false, new NewPasswordChangeRequestDTO(userRequested.getEmail(),
                    userRequested.getCodigoValidacion(), userRequested.getNombre(), userRequested.getApellido(),
                    userRequested.getRol(), userRequested.getImagen_url()));
        } else {
            return new NewPasswordChangeRequestResponse(Long.valueOf(401), "El usuario no se ha encontrado", true, null);
        }

    }
}
