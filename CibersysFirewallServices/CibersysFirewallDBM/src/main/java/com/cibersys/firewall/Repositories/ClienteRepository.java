package com.cibersys.firewall.Repositories;

import com.cibersys.firewall.Domain.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Luis Maracara on 6/14/2017.
 */
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente,Long> {


}
