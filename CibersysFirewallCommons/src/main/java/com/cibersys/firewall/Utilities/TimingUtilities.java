package com.cibersys.firewall.Utilities;


import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Period p = new Period(new DateTime(expirationDate),new DateTime(new Date()));
        return p.getHours() >= 2  ||
                p.getDays() != 0 ||
                p.getMonths() != 0 ||
        p.getYears() != 0 ? true : false;
        }

/**
 * Formateador de fechas estandar.
 *
 * **/
  private Date standarApplicationDateFormatter(Date old_format_date){
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      try {
          return formatter.parse(old_format_date.toString());
      } catch (ParseException e) {
          e.printStackTrace();
          return null;
      }
  }
}
