package com.cibersys.firewall.autorization.Services.Utilities;

import com.cibersys.firewall.domain.models.DTO.model.NewPasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.model.PasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserUpdateRequestDTO;
import org.springframework.http.HttpEntity;

/**
 * Created by Luis Maracara on 6/19/2017.
 */
public interface UserGeneralRequestBuilder {
    HttpEntity<UserDTO> buildUserDTORequest(UserDTO body);
    HttpEntity<UserUpdateRequestDTO> buildUserUpdateDTORequest(UserUpdateRequestDTO u);
    HttpEntity<PasswordChangeRequestDTO> buildPasswordChangeRequestDTO(PasswordChangeRequestDTO u);
    HttpEntity<NewPasswordChangeRequestDTO> buildNewPasswordChangeRequestDTO(NewPasswordChangeRequestDTO u);
}
