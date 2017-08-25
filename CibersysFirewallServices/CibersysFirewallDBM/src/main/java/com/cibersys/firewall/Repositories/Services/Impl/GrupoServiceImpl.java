package com.cibersys.firewall.Repositories.Services.Impl;

import com.cibersys.firewall.Domain.Model.Grupo;
import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.GrupoRepository;
import com.cibersys.firewall.Repositories.Services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
@Service
@Transactional
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository repository;

    @Override
    public Grupo guardarGrupo(Grupo gru) {
        return repository.save(gru);
    }

    @Override
    public Grupo obtenerGrupo(Grupo grp) {
        return repository.findOne(grp.getIdGrupo());
    }

    @Override
    public List<Grupo> getAllGroupsByUsuarioCreacion(Usuario user) {
        return repository.findAllByUsuarioCreacion(user);
    }

    @Override
    public List<Grupo> obtenerTodosLosGruposPorIdCliente(Long idCliente) {
        return repository.findAllGroupsByClient(idCliente);
    }
}
