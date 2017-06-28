package com.cibersys.firewall.Utilities;

import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserUpdateRequestDTO;
import org.springframework.http.HttpEntity;

/**
 * Created by AKDESK25 on 6/19/2017.
 */
public interface UserGeneralRequestBuilder {
    HttpEntity<UserDTO> buildUserDTORequest(UserDTO body);
    HttpEntity<UserUpdateRequestDTO> buildUserUpdateDTORequest(UserUpdateRequestDTO u);
}
