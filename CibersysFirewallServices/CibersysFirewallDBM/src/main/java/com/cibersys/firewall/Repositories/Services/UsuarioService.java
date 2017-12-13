package com.cibersys.firewall.Repositories.Services;

import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Usuario;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Maracartman on 31/7/2017.
 */
public interface UsuarioService {

    Usuario getUserByEmailAndEncryptedPasswordAndEstatus(@Param("user") String email, @Param("password") String encrypted, @Param("estatus") String estatus);

    Usuario getUserByEmailAndCodigoValidacionAndEstatus(String email,String codigoValidacion,String estatus);

    Usuario getUserByEmailAndEstatus(String email,String estatus);

    Usuario getUserByEmail(String email);

    Usuario saveUsuario(Boolean is_new,Usuario user);

    Usuario getUserById(Long id);

    Usuario find(Long usuario);

    Usuario getClientAdministratorByCliente(Cliente cliente);

    List<Usuario> getAllUsuario();

    List<Usuario> getAllUsuarioByRolAndCliente(String rol, Cliente cli);

    List<Usuario> getAllUsuarioByCliente(Cliente cli);

    List<Usuario> getAllUsuariosByRol(String rol);
}
