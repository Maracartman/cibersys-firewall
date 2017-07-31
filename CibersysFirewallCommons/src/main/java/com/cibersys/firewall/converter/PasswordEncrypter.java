package com.cibersys.firewall.converter;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Luis Maracara on 6/19/2017.
 */
@Service
public class PasswordEncrypter {
    private static MessageDigest md;
    public static String cryptWithMD5(String pass){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordEncrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Boolean comparePassword(String passByUser,String passEncrypted){
        return cryptWithMD5(passByUser).equals(passEncrypted) ? true : false;
    }
}
