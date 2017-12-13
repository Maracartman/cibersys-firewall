package com.cibersys.firewall.Repositories;

import com.cibersys.firewall.Domain.Model.UsuarioCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
public interface UsuarioClienteRepository extends JpaRepository<UsuarioCliente,Long> {

    List<UsuarioCliente> findByIdUsuarioClienteIn(ArrayList<Long> ids);
}
