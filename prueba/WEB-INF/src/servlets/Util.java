package servlets;

import javax.servlet.http.HttpServletRequest;

public class Util {

	public static String print (HttpServletRequest req, String nombre) {
		String s = (String) req.getAttribute(nombre);
		if (s == null)
			return "";
		else
			return s;
	}
	
	
}
