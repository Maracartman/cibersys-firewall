package com.cibersys.firewall.Repositories.Services;

import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Usuario;

/**
 * Created by Maracartman on 31/7/2017.
 */
public interface ClientService {

    Cliente getClientByUsuarioActivacionAndEstaus(Usuario usuario_activacion, String estatus);
}
