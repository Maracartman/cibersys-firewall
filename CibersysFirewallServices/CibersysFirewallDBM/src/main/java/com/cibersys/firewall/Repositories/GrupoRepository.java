package com.cibersys.firewall.Repositories;

import com.cibersys.firewall.Domain.Model.Grupo;
import com.cibersys.firewall.Domain.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
public interface GrupoRepository extends JpaRepository<Grupo,Long> {

    List<Grupo> findAllByUsuarioCreacion(Usuario usuario_creacion);

    @Query(value = "select distinct g.* from grupo g inner join usuario u on g.usuario_creacion = u.idusuario where u.idcliente = ?1 order by g.nombre")
    List<Grupo> findAllGroupsByClient(Long idCliente);
}
