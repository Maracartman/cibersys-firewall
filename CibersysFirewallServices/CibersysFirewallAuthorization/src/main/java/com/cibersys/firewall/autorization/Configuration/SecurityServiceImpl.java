package com.cibersys.firewall.autorization.Configuration;

import com.cibersys.firewall.security.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by AKDESK04 on 3/28/2017.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Boolean hasProtectedAccess() {
        return (SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
    }

}
