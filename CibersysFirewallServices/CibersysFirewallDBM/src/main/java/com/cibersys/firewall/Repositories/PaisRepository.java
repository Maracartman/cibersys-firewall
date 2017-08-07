package com.cibersys.firewall.Repositories;

import com.cibersys.firewall.Domain.Model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Luis Maracara on 6/14/2017.
 */
@Transactional
@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface PaisRepository extends PagingAndSortingRepository<Pais, Long> {
    List<Pais> findByIdpais(@Param("id") Long id);

    List<Pais> findAll();

    Pais findOneByidpais(Long id);

}
