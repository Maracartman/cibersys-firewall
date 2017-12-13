package com.cibersys.firewall.Repositories.Services;

import com.cibersys.firewall.Domain.Model.UsuarioCliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
public interface UsuarioClienteService {
    UsuarioCliente guardarUsuarioCliente(UsuarioCliente usuarioCliente);
    List<UsuarioCliente> obtenerUsuariosClientePorGrupo(ArrayList<Long> ids);
}
