package com.cibesys.firewall.mailer.Configuration;

public class ConfigurationProperties {
	
	/**
	 * clave privada para encriptar
	 */
	public final static String PRIVATE_PASSWORD = 
			"$@Z6pqFf#C@VEUwYR$RPm9Jrau@2WmKtf8tRZH4uSA+cWv&Pxe9$8e5$4*9sCp6=";
	
	/**
	 * clase privada para encriptar el token publico
	 */
	public final static String PUBLIC_PASSWORD = 
			"~5p`Z[j\\S<beFwz/;Kbf&Y3g.+9+m[D<h!JaVyA6jV(rfBRj,c,U'NJ?BD=@*{*a5";
	
	public final static String NAME_APP = "APPCiBERSYS";
	
	public final static String ID_APP = "IDCiBERSIS";
	
	
	public final static String PATH_PUBLIC = "/api/v1/public/";
			
	public final static String PATH_PRIVATE = "/api/v1/private/";

	
	
	public final static int EXTIRATION_HOURS_TOKEN = 730;
	
	
	public final static int MAX_RESULT_PAGINATOR = 10;
	
	
	
	
	//variables para email
	public final static String SUBJECT_EMAIL_NEW_PASSWORD = "Cambio realizado con éxito - Cibersys Firewall";

	public final static String SUBJECT_EMAIL_RECUPERATE_PASSWORD = "Ha solicitado cambio de contraseña - Cibersys Firewall";

	public final static String SUBJECT_EMAIL_WELCOME = "Bienvenido a Cibersys Firewall";

	public final static String SUBJECT_BILLING = "Facturación Cibersys Firewall al ";
	
	public final static String EMAIL_BACKEND = "cibersys@example.ve";
	
	public final static String PATH_EMAIL_RECUPERATE_PASSWORD = "emails/Solicitud.html";
	
	public final static String CIBERSYS_HEADER_EMAIL = "emails/Solicitud_archivos/head.png";

	public final static String CIBERSYS_LOGO_EMAIL = "emails/General_resources/cibersys-logo.png";


	public final static String CIBERSYS_LOGO_FACEBOOK = "emails/General_resources/facebook.png";

	public final static String CIBERSYS_LOGO_GOOGLE = "emails/General_resources/google.png";


	public final static String CIBERSYS_LOGO_LINKEDIN = "emails/General_resources/linkedin.png";


	public final static String CIBERSYS_LOGO_TWITTER = "emails/General_resources/twitter.png";

	//correo exito
	public final static String PATH_EMAIL_SUCCESS = "emails/Exito.html";
	
	public final static String CIBERSYS_BANNER_HEAD_SUCCESS = "emails/Exito_archivos/banner-2.png";
	public final static String CIBERSYS_CHECK_ICON_SUCCESS = "emails/Exito_archivos/check-icon.png";
	public final static String CIBERSYS_LOGO_ICON_SUCCESS = "emails/General_resources/cibersys-logo.png";


	
	public final static String QR_LOGIN = "qr_login.png";

	//correo factura

	public final static String PATH_EMAIL_BILLING_INFO = "emails/Factura.html";



	public final static String CIBERSYS_LOGO_EMAIL_BILLING = "emails/Factura_archivos/cibersys-logo.png";

	public final static String CIBERSYS_LOGO_FACEBOOK_BILLING = "emails/Factura_archivos/facebook.png";

	public final static String CIBERSYS_LOGO_GOOGLE_BILLING = "emails/Factura_archivos/google.png";

	public final static String CIBERSYS_LOGO_HEAD_BILLING = "emails/Factura_archivos/head.png";

	public final static String CIBERSYS_LOGO_LINKEDIN_BILLING = "emails/Factura_archivos/linkedin.png";

	public final static String CIBERSYS_LOGO_PROFILE_PICTURE_BILLING = "D:/Cibersys/svn/Code/branches/externalResources/profileImages/archivo3.jpg";

	public final static String CIBERSYS_LOGO_TWITTER_BILLING = "emails/Factura_archivos/twitter.png";


	public final static String CIBERSYS_LOGO_DOWNLOAD_REPORT_BILLING = "emails/Factura_archivos/descargarReporte.png";

	public final static String CIBERSYS_LOGO_SEE_REPORT_BILLING = "emails/Factura_archivos/verReporte.png";


}
