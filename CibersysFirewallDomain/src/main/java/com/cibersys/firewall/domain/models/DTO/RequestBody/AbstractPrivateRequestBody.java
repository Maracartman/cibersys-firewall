package com.cibersys.firewall.domain.models.DTO.RequestBody;

import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * Created by Maracartman on 25/7/2017.
 */
@Data
@AllArgsConstructor
public abstract class AbstractPrivateRequestBody<T extends ArrayList<?>> {

    @JsonProperty("request")
    protected T request;

    public AbstractPrivateRequestBody(){ }
}
