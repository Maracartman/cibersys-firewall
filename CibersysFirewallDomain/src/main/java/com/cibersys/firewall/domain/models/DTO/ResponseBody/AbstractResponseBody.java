package com.cibersys.firewall.domain.models.DTO.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Created by Luis Maracara on 6/14/2017.
 */
@Data
@AllArgsConstructor
public abstract  class AbstractResponseBody<T extends Object>{

    @JsonProperty("code")
    protected Long code;

    @JsonProperty("message")
    protected String message;

    @JsonProperty("error")
    protected Boolean error;

    @JsonProperty("response")
    protected T response;

    public AbstractResponseBody(){};
}
