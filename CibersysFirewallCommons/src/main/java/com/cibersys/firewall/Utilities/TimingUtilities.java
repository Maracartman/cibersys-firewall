package com.cibersys.firewall.Utilities;


import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * Created by Luis Maracaras on 6/27/2017.
 */
@Service
public class TimingUtilities {
/**
 * Dado una fecha se verifica si ya transcurrieron las dos horas limites.
 * **/
    public Boolean isTheVerificationCodeExpired(Date expirationDate){
        Period p = new Period(new DateTime(new Date()),new DateTime(expirationDate));
        return p.getHours() >= 2 ? true : false;
    }
}