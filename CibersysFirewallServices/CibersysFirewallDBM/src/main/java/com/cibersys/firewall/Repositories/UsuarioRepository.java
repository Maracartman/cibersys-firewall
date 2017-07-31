package com.cibersys.firewall.Repositories;



import com.cibersys.firewall.Domain.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Luis Maracara on 6/14/2017.
 */
@Transactional
@RepositoryRestResource(collectionResourceRel = "usuario", path = "usuario")
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findOneByEmailAndContraseñaAndEstatus(@Param("user") String email,@Param("password") String encriptedPassword,@Param("estatus") String estatus);

    List<Usuario> findByEmailAndContraseña(@Param("email") String email, @Param("password") String encriptedPassword);

    Usuario findByEmailAndCodigoValidacionAndEstatus(String email,String codigoValidacion,String estatus);

    Usuario findByEmailAndEstatus(String email,String estatus);


    Usuario findByEmail(String email);
}

