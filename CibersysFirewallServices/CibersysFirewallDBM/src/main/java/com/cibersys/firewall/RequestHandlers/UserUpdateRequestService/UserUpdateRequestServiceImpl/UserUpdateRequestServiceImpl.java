package com.cibersys.firewall.RequestHandlers.UserUpdateRequestService.UserUpdateRequestServiceImpl;

import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.UsuarioRepository;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.UserUpdateRequestService.UserUpdateRequestService;
import com.cibersys.firewall.Utilities.TimingUtilities;
import com.cibersys.firewall.converter.PasswordEncrypter;
import com.cibersys.firewall.domain.models.DTO.model.UserUpdateRequestDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserUpdateResponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.UpdateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/19/2017.
 */
@Service
public class UserUpdateRequestServiceImpl extends AbstractRequestHandler<UpdateResponse> implements UserUpdateRequestService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private PasswordEncrypter encrypter;

    @Autowired
    private TimingUtilities timingUtilities;
    @Override
    public UpdateResponse proceedRequest(Map<String, String> body) {
        UserUpdateRequestDTO u = objectMapper.convertValue(body,UserUpdateRequestDTO.class);
        Usuario usuario = usuarioRepository.findByEmailAndCodigoValidacionAndEstatus(u.getEmail(),
               u.getVerificationCode(),"1");
        if(usuario != null){
            if(!timingUtilities.isTheVerificationCodeExpired(usuario.getFechaCodigoValidacion())){
                usuario.setContraseña(encrypter.cryptWithMD5(u.getNewPassword()));
                usuarioRepository.save(usuario);
                return new UpdateResponse(Long.valueOf(200),"El usuario ha sido actualizado.",false,new UserUpdateResponseDTO(
                        u.getEmail(),u.getNewPassword()));
            }else{
                return new UpdateResponse(Long.valueOf(200),"El código de validación ha expirado por favor vuelva a solicitar el cambio de contraseña.",true,
                        null);
            }



        }
        else{
            return new UpdateResponse(Long.valueOf(401),"código de validación o correo no válidos",
                    true,null);
        }
    }
}
