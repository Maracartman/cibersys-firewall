package com.cibersys.firewall.Repositories.Services;

import com.cibersys.firewall.Domain.Model.Grupo;
import com.cibersys.firewall.Domain.Model.Usuario;

import java.util.List;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
public interface GrupoService {
    Grupo guardarGrupo(Grupo gru);
    Grupo obtenerGrupo(Grupo grp);
    List<Grupo> getAllGroupsByUsuarioCreacion(Usuario user);
    List<Grupo> obtenerTodosLosGruposPorIdCliente(Long idCliente);

}
