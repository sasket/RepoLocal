package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BaseDatos;

@WebServlet(name = "EditarSubmit", urlPatterns = {"/editarSubmit.do"})
public class EditarSubmit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String nombre = req.getParameter("nom");
		String apellidos = req.getParameter("ape");
		String telefono = req.getParameter("tel");
		
		String destino;
		if (nombre.trim().equals("")) {
			req.setAttribute("msg", "El campo nombre no puede estar en vacio<br>");
			destino = "/editar.do?id=" + id;
		} else {
			String sql = "update usuarios set nombre = ?, "
				+ "apellidos = ?, telefono = ? where id = ?";
			BaseDatos bd = new BaseDatos();
			bd.conectar();
			bd.PreparedStatement(sql);
			bd.setString(1, nombre);
			bd.setString(2, apellidos);
			bd.setString(3, telefono);
			bd.setInt(4, Integer.parseInt(id));
			int valor = bd.executeUpdate();
			bd.close();
			destino = "/listado.do";
			if (valor == 1)
				req.setAttribute("msg", "Registro editado correctamente<br>");
			else
				req.setAttribute("msg", "Error al editar<br>");
		}
		
		req.getRequestDispatcher(destino).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
}
