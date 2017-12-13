package com.cibersys.firewall.Repositories;

import com.cibersys.firewall.Domain.Model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
public interface SolicitudRepository extends JpaRepository<Solicitud,Long> {
}
