package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BaseDatos;

@WebServlet(name = "InsertarSubmit", urlPatterns = {"/insertarSubmit.do"})
public class InsertarSubmit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nombre = req.getParameter("nom");
		String apellidos = req.getParameter("ape");
		String telefono = req.getParameter("tel");
		
		String destino;
		if (nombre.trim().equals("")) {
			destino = "/insertar.do";
			req.setAttribute("msg", "El nombre es obligatorio");
		} else {
			String sql = "insert into usuarios (nombre, apellidos, telefono) "
					+ "values (?, ?, ?)";
			BaseDatos bd = new BaseDatos();
			bd.conectar();
			bd.PreparedStatement(sql);
			bd.setString(1, nombre);
			bd.setString(2, apellidos);
			bd.setString(3, telefono);
			int valor = bd.executeUpdate();
			System.out.println("Insert:" + valor);
			bd.close();
			
			if (valor == 1)
				req.setAttribute("msg", "Nuevo cliente añadido");
			else
				req.setAttribute("msg", "Error al insertar");
			destino = "/index.jsp";
		}
		
		req.getRequestDispatcher(destino).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
	
}
