package com.cibersys.firewall.Repositories.Services;

import com.cibersys.firewall.Domain.Model.Pais;

import java.util.List;

/**
 * Created by Maracartman on 31/7/2017.
 */
public interface PaisService {

    List<Pais> getAllPaises();
    Pais getPaisById(String id);
}
