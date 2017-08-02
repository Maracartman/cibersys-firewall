package com.cibersys.firewall.Repositories.Services.Impl;

import com.cibersys.firewall.Domain.Model.Pais;
import com.cibersys.firewall.Repositories.PaisRepository;
import com.cibersys.firewall.Repositories.Services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Maracartman on 31/7/2017.
 */
@Service
@Transactional
public class PaisServiceImpl implements PaisService {

    @Autowired
    private PaisRepository repository;


    @Override
    public List<Pais> getAllPaises() {
        return repository.findAll();
    }

    @Override
    public Pais getPaisById(Long id) {
        return repository.findOneByidpais(id);
    }
}
