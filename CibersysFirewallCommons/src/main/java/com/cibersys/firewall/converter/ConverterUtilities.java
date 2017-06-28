package com.cibersys.firewall.converter;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Luis Maracara on 6/27/2017.
 */
@Service
public class ConverterUtilities {

/**
 * Dado @fecha
 * y un format para el SimpleDateFormat
 * retorna la fecha dada con el formato deseado.
 * **/
    public Date getFormattedDate(String format,Date fecha){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(sdf.format(fecha));
        }catch (Exception e){
            return null;
        }
    }
}
