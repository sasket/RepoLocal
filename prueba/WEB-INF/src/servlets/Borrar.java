package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BaseDatos;

@WebServlet(name = "Borrar", urlPatterns = {"/borrar.do"})
public class Borrar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String sql = "delete from usuarios where id = ?";
		BaseDatos bd = new BaseDatos();
		bd.conectar();
		bd.PreparedStatement(sql);
		bd.setInt(1, Integer.parseInt(id));
		bd.executeUpdate();
		bd.close();
		
		req.getRequestDispatcher("/listado.do").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
}
