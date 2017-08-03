package com.cibersys.firewall.Utilities;


import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Random;
/**
 * 
 * maneja opciones de los token
 * @author leonelsoriano3@gmail.com
 *
 */
@Service
public class ManagerToken {
	/**
	 * genera un token aleatorio para la recuperacion de la clave
	 * este va al email del solicitante
	 * @return token aleatorio
	 */
	public String generateRandomToken(){
		
		StringBuilder stringBuilder = new StringBuilder();
		
		Long unixTime = System.currentTimeMillis() / 1000L;
		
		
	    final String alphabet = "123456789ZXCVBNMASDFGHJKLQWERTYUIOP";
	    final int N = alphabet.length();

	    Random r = new Random();

	    for (int i = 0; i < 10; i++) {
	    	stringBuilder.append(alphabet.charAt(r.nextInt(N)));
	    }
		
	    stringBuilder.append(unixTime.toString());
		return stringBuilder.toString().substring(7,13);
	}
/**
 * Metodo para crear una contraseña aleatoria para la primera vez que se crea un usuario
 * Default = 15;
 *
 * **/
	public String generateRandomPassword(Integer characternumbers){

		Integer characters_number = characternumbers  == null ? 15 : characternumbers;

		StringBuilder stringBuilder = new StringBuilder();

		Long unixTime = System.currentTimeMillis() / 1000L;


		final String alphabet = "123456789ZXCVBNMASDFGHJKLQWERTYUIOP";
		final int N = alphabet.length();

		Random r = new Random();

		for (int i = 0; i < characternumbers+5; i++) {
			stringBuilder.append(alphabet.charAt(r.nextInt(N)));
		}

		stringBuilder.append(unixTime.toString());
		return stringBuilder.toString().substring(0,characternumbers);
	}
	
	
	/**
	 * genera un password temporal para la aplicacion
	 * @return token aleatorio
	 */
	public String generateTemporalPassword(){
		
		 StringBuilder builder = new StringBuilder();
		 
		 builder.append("TMP");
		 builder.append(this.generateRandomToken());
		 return builder.toString();
	}

	public Boolean checkNull(Object T){
		try{
			for (Field f : T.getClass().getDeclaredFields())
				if (f.get(this) == null)
					return false;
			return true;
		}catch (IllegalAccessException  e){
			return null;
		}

	}
}
