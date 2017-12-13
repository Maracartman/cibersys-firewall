package com.cibersys.firewall.Utilities;

import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Grupo;
import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Domain.Model.UsuarioCliente;
import com.cibersys.firewall.domain.models.DTO.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maracartman on 7/8/2017.
 */
@Service
public class DBMServicesImpl implements DBMServices {
    @Override
    public ClientDTO mapToClienteDTO(Cliente cliente) {
        return new ClientDTO(cliente.getNombre(),cliente.getDireccion(),cliente.getTelefono1(),
                cliente.getIdcliente(),cliente.getPais().getIdpais(),cliente.getFechaActivacion().toString());
    }

    @Override
    public UserInfoDTO mapToUserInfoDTO(Usuario usuario) {
        return  new UserInfoDTO(usuario.getEmail(), null,
                Integer.parseInt(usuario.getRol()),
                usuario.getNombre(), usuario.getApellido(),
                false,usuario.getIdusuario(),usuario.getEstatus().equals("1")?false:true);
    }

    @Override
    public Grupo mapToGrupo(GroupDTO grp) {
        return new Grupo(null,grp.getNombre(),grp.getDescripcion(),grp.getDireccionIp(),grp.getTipoIp(),
                grp.getEstatus(),null,null,null);
    }

    @Override
    public List<UserClientDTO> mapToUserClientDTOList(List<UsuarioCliente> usuariosClientes) {
        List<UserClientDTO> returnList = new ArrayList<>();
        usuariosClientes.forEach(uc -> {
            returnList.add(new UserClientDTO(uc.getIdUsuarioCliente(),uc.getNombre(),uc.getApellido(),uc.getEmail(),uc.getDireccionIp(),
                    uc.getCliente().getIdcliente(),uc.getUsuarioCreacion().getIdusuario(),uc.getFechaCreacion().toString(),uc.getEstatus()));
        });
        return returnList;
    }

    @Override
    public GroupDTO mapToGroupDTO(Grupo save) {
        return new GroupDTO(save.getIdGrupo(),save.getNombre(),save.getDescripcion(),save.getDireccionIp(),save.getTipoIp(),
                save.getEstatus(),save.getFechaCreacion().toString(),mapToUserDTO(save.getUsuarioCreacion()));
    }

    private UserDTO mapToUserDTO(Usuario uc) {
        return new UserDTO(uc.getEmail(),null,uc.getRol(),uc.getNombre(),uc.getApellido());
    }
}
