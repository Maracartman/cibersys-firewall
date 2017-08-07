package com.cibersys.firewall.Utilities;

import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.domain.models.DTO.model.ClientDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserInfoDTO;

/**
 * Created by Maracartman on 7/8/2017.
 */
public interface DBMServices {
    ClientDTO mapToClienteDTO(Cliente cliente);
    UserInfoDTO mapToUserInfoDTO(Usuario usuario);
}
