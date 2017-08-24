package com.cibersys.firewall.Repositories.Services.Impl;

import com.cibersys.firewall.Domain.Model.UsuarioCliente;
import com.cibersys.firewall.Repositories.Services.UsuarioClienteService;
import com.cibersys.firewall.Repositories.UsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
@Service
public class UsuarioClienteServiceImpl implements UsuarioClienteService {

    @Autowired
    private UsuarioClienteRepository repository;

    @Override
    public UsuarioCliente guardarUsuarioCliente(UsuarioCliente usuarioCliente) {
        return repository.save(usuarioCliente);
    }
}
