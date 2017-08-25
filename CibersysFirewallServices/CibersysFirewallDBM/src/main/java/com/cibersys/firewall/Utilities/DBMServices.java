package com.cibersys.firewall.Utilities;

import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Grupo;
import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Domain.Model.UsuarioCliente;
import com.cibersys.firewall.domain.models.DTO.model.ClientDTO;
import com.cibersys.firewall.domain.models.DTO.model.GroupDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserClientDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by Maracartman on 7/8/2017.
 */
public interface DBMServices {
    ClientDTO mapToClienteDTO(Cliente cliente);
    UserInfoDTO mapToUserInfoDTO(Usuario usuario);
    Grupo mapToGrupo(GroupDTO grp);
    List<UserClientDTO> mapToUserClientDTOList(List<UsuarioCliente> usuariosClientes);
    GroupDTO mapToGroupDTO(Grupo save);
}
