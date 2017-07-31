package com.cibersys.firewall.Repositories.Services;

import com.cibersys.firewall.Domain.Model.Usuario;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Maracartman on 31/7/2017.
 */
public interface UsuarioService {

    Usuario getUserByEmailAndEncryptedPasswordAndEstatus(@Param("user") String email, @Param("password") String encrypted, @Param("estatus") String estatus);

    List<Usuario> getAllUsers(@Param("email") String email, @Param("password") String encriptedPassword);

    Usuario getUserByEmailAndCodigoValidacionAndEstatus(String email,String codigoValidacion,String estatus);

    Usuario getUserByEmailAndEstatus(String email,String estatus);

    Usuario getUserByEmail(String email);

    Usuario saveUsuario(Boolean is_new,Usuario user);
}
