package com.cibersys.firewall.Repositories;

import com.cibersys.firewall.Domain.Model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Luis Maracara on 6/14/2017.
 */
@Transactional
public interface PaisRepository extends JpaRepository<Pais,Long> {


    List<Pais> findAll();
    Pais findOneByidpais(Long id);

}
