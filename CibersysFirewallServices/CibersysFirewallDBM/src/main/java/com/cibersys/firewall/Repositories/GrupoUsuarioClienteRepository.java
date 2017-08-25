package com.cibersys.firewall.Repositories;

import com.cibersys.firewall.Domain.Model.GrupoUsuarioCliente;
import com.cibersys.firewall.Domain.Model.GrupoUsuarioClienteKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
public interface GrupoUsuarioClienteRepository extends
        JpaRepository<GrupoUsuarioCliente,Long> {
}
