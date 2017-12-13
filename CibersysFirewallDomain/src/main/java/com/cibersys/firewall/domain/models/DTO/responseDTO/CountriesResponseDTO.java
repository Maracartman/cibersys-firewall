package com.cibersys.firewall.domain.models.DTO.responseDTO;

import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import lombok.Data;

import java.util.List;

/**
 * Created by Maracartman on 7/8/2017.
 */
@Data
public class CountriesResponseDTO extends AbstractResponseBody<List<CountriesResponse>> {

    public CountriesResponseDTO(Long code, String message, Boolean error, List<CountriesResponse> response) {
        super(code, message, error, response);
    }

    public CountriesResponseDTO() {
    }
}
