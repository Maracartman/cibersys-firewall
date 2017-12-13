package com.cibersys.firewall.RequestHandlers.CountriesService.Impl;

import com.cibersys.firewall.Domain.Model.Pais;
import com.cibersys.firewall.Repositories.Services.PaisService;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.CountriesService.CountriesService;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.responseDTO.CountriesResponse;
import com.cibersys.firewall.domain.models.DTO.responseDTO.CountriesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Maracartman on 7/8/2017.
 */
@Service
public class CountriesServiceImpl extends AbstractRequestHandler<AbstractResponseBody>
        implements CountriesService {
    @Autowired
    private PaisService service;

    @Override
    public AbstractResponseBody proceedRequest(Map<String, String> body, Map<String, String> header) {
        List<CountriesResponse> response = null;
        if(body.get("id")== null){
            if(service.getAllPaises().size()!=0){
                response = new ArrayList<>();
                for(Pais p :  service.getAllPaises()){
                    response.add(new CountriesResponse(p.getIdpais(),p.getIso(),p.getNombre(),p.getCodigo_telefono()));
                }
                return new CountriesResponseDTO(Long.valueOf(200),"",false,response);
            }else{
                return new CountriesResponseDTO(Long.valueOf(200),"No hay ningún país en la " +
                        "base de datos.",true,response);
            }

        }else{
            Pais p = service.getPaisById(Long.valueOf(body.get("id")));
            if(p != null) {
                response = new ArrayList<>();
                response.add(new CountriesResponse
                        (p.getIdpais(),p.getIso(),p.getNombre(),p.getCodigo_telefono()));
            }else{
                return new CountriesResponseDTO(Long.valueOf(200),"No hay ningún país con el ID enviado en la " +
                        "base de datos.",true,response);
            }

            return new CountriesResponseDTO(Long.valueOf(200),"",false,response);
        }
    }
}
