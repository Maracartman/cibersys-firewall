package com.cibersys.firewall.Repositories.Services.Impl;

import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.ClienteRepository;
import com.cibersys.firewall.Repositories.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Maracartman on 31/7/2017.
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente getClientByUsuarioActivacionAndEstaus(Usuario usuario_activacion, String estatus) {
        return repository.findByUsuarioActivacionAndEstatus(usuario_activacion,estatus);
    }
}