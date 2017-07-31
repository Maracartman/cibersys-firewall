package com.cibersys.firewall.Repositories.Services.Impl;

import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.Services.UsuarioService;
import com.cibersys.firewall.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Maracartman on 31/7/2017.
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;


    @Override
    public Usuario getUserByEmailAndEncryptedPasswordAndEstatus(String email, String encrypted, String estatus) {
        return repository.findOneByEmailAndContraseñaAndEstatus(email,encrypted,estatus);
    }

    @Override
    public List<Usuario> getAllUsers(String email, String encriptedPassword) {
        return repository.findByEmailAndContraseña(email,encriptedPassword);
    }

    @Override
    public Usuario getUserByEmailAndCodigoValidacionAndEstatus(String email, String codigoValidacion, String estatus) {
        return repository.findByEmailAndCodigoValidacionAndEstatus(email,codigoValidacion,estatus);
    }

    @Override
    public Usuario getUserByEmailAndEstatus(String email, String estatus) {
        return repository.findByEmailAndEstatus(email,estatus);
    }

    @Override
    public Usuario getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Usuario saveUsuario(Boolean is_new, Usuario user) {
        if(is_new){
            if(repository.findByEmail(user.getEmail()) != null) return null;
            else return repository.save(user);
        }else{
            if(repository.findByEmail(user.getEmail()) == null) return null;
            else
                return repository.save(user);

        }    }
}