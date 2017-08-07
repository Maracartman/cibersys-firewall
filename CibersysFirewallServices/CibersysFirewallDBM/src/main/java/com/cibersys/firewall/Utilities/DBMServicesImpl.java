package com.cibersys.firewall.Utilities;

import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.domain.models.DTO.model.ClientDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserInfoDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Maracartman on 7/8/2017.
 */
@Service
public class DBMServicesImpl implements DBMServices {
    @Override
    public ClientDTO mapToClienteDTO(Cliente cliente) {
        return new ClientDTO(cliente.getNombre(),cliente.getDireccion(),cliente.getTelefono1(),
                cliente.getIdcliente(),cliente.getPais().getIdpais());
    }

    @Override
    public UserInfoDTO mapToUserInfoDTO(Usuario usuario) {
        return  new UserInfoDTO(usuario.getEmail(), null,
                Integer.parseInt(usuario.getRol()),
                usuario.getNombre(), usuario.getApellido(),
                false,usuario.getIdusuario());
    }
}
