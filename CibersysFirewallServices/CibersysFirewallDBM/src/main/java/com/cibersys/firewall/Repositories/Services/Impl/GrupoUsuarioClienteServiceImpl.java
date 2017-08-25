package com.cibersys.firewall.Repositories.Services.Impl;

import com.cibersys.firewall.Domain.Model.GrupoUsuarioCliente;
import com.cibersys.firewall.Repositories.GrupoUsuarioClienteRepository;
import com.cibersys.firewall.Repositories.Services.GrupoUsuarioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
@Service
@Transactional
public class GrupoUsuarioClienteServiceImpl implements GrupoUsuarioClienteService {

    @Autowired
    private GrupoUsuarioClienteRepository repository;

    @Override
    public GrupoUsuarioCliente guardarGrupoUsuarioCliente(GrupoUsuarioCliente guc) {
        return repository.save(guc);
    }
}
