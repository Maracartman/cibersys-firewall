package com.cibersys.firewall.autorization.Services.RequestHandlers.CountryService.CountryServiceImpl;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.autorization.Services.RequestHandlers.CountryService.CountryService;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.CountriesResponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.security.TokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Created by Maracartman on 7/8/2017.
 */
@Service
public class ContryServiceImpl extends AbstractRequestHandler<ResponseEntity<?>> implements CountryService {
    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body, Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        HttpEntity<Map<String, String>> request = userGeneralRequestBuilder.buildCountriesRequest(body);
        try {
            CountriesResponseDTO consult = restTemplate.postForObject(dbmRoute + dbmCountries, request, CountriesResponseDTO.class);
            if(!consult.getError()){
                return ResponseEntity.ok(consult);
            }else{
                return ResponseEntity.ok(new ResponseError(Long.valueOf(401),consult.getMessage(),
                        true,null));
            }
        } catch (Exception e) {

        }
        return null;

        /*
        tokenUtils = new TokenUtils(secret, expiration);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        try {
            headers.add("X-Auth-Token",
                    tokenUtils.generateToken(objectMapper.writeValueAsString(
                            new UserDTO("internal@cibersys", "internal@cibersys"))));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url = dbmRoute+dbmCountries;
        if(body.get("id")!= null)
            url = url+"/"+body.get("id");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);*/
    }
}
