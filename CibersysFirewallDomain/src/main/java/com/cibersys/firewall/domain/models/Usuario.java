package com.cibersys.firewall.domain.models;

import com.cibersys.firewall.domain.Base;
import lombok.Value;

/**
 * Created by AKDESK25 on 6/12/2017.
 */
@Value
public class Usuario extends Base {
    String username,password;
}
