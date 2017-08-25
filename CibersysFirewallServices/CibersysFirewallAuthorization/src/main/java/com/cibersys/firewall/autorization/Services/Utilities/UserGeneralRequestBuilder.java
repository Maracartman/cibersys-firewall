package com.cibersys.firewall.autorization.Services.Utilities;

import com.cibersys.firewall.domain.models.DTO.RequestDTO.*;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import org.springframework.http.HttpEntity;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/19/2017.
 */
public interface UserGeneralRequestBuilder {
    HttpEntity<UserDTO> buildUserDTORequest(UserDTO body);
    HttpEntity<UserUpdateRequestDTO> buildUserUpdateDTORequest(UserUpdateRequestDTO u);
    HttpEntity<PasswordChangeRequestDTO> buildPasswordChangeRequestDTO(PasswordChangeRequestDTO u);
    HttpEntity<NewPasswordChangeRequestDTO> buildNewPasswordChangeRequestDTO(NewPasswordChangeRequestDTO u);
    HttpEntity<PasswordChangeRequest> buildPasswordChangeRequestDTO(PasswordChangeRequest u);
    HttpEntity<SetUsuarioRequestDTO> buildSetUsuarioRequestDTO(SetUsuarioRequestDTO setUsuarioRequest, Map<String, String> header);
    HttpEntity<NewPanelClientRequestDTO> buildSetUsuarioRequestDTO(NewPanelClientRequestDTO setClienteRequest, Map<String, String> header);
    HttpEntity<GroupRequestDTO> buildGroupRequestDTO(GroupRequestDTO groupRequest, Map<String, String> header);

    HttpEntity<Map<String,String>> buildCountriesRequest(Map<String, String> body);
}
