package com.cibersys.firewall.RequestHandlers.UserUpdateRequestService.UserUpdateRequestServiceImpl;

import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.UsuarioRepository;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.UserUpdateRequestService.UserUpdateRequestService;
import com.cibersys.firewall.Utilities.TimingUtilities;
import com.cibersys.firewall.converter.PasswordEncrypter;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.UserUpdateRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.UserUpdateResponseDTO;
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
    public UpdateResponse proceedRequest(Map<String, String> body,Map<String, String> header) {
        UserUpdateRequestDTO u = objectMapper.convertValue(body,UserUpdateRequestDTO.class);
        Usuario usuario = usuarioRepository.findByEmail(u.getEmail());
        if(usuario != null){
            Boolean proceed = false;
            String msj = "El usuario ha sido actualizado.";
            if(usuario.getCodigoValidacion() != null)
                if(!timingUtilities.isTheVerificationCodeExpired(usuario.getFechaCodigoValidacion()))
                    proceed = true;
                else msj = "El código de validación ha expirado por favor vuelva a solicitar el cambio de contraseña.";
            else
                if(passwordEncrypter.comparePassword(u.getVerificationCode(),usuario.getContraseña()))
                    proceed = true;
            else msj = "La contraseña con la que se verifica no es correcta.";
                if(proceed){
                    usuario.setContraseña(encrypter.cryptWithMD5(u.getNewPassword()));
                    usuarioRepository.save(usuario);
                    return new UpdateResponse(Long.valueOf(200),msj,false,new UserUpdateResponseDTO(
                            u.getEmail(),u.getNewPassword()));
                }
            else{
                return new UpdateResponse(Long.valueOf(200),"",true,
                        null);
            }



        }
        else{
            return new UpdateResponse(Long.valueOf(401),"código de validación o correo no válidos",
                    true,null);
        }
    }
}
