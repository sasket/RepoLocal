package servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BaseDatos;
@WebServlet(name = "Editar", urlPatterns = {"/editar.do"})
public class Editar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		BaseDatos bd = new BaseDatos();
		bd.conectar();
		String sql = "select * from usuarios where id = ?";
		bd.PreparedStatement(sql);
		bd.setInt(1, Integer.parseInt(id));
		ResultSet rs = bd.executeQuery();
		String nombre = null;
		String apellidos = null;
		String telefono = null;
		try {
			rs.next();
			nombre = rs.getString("nombre");
			apellidos = rs.getString("apellidos");
			telefono = rs.getString("telefono");
		} catch (Exception e) {
			e.printStackTrace();
		}
		bd.close();
		req.setAttribute("id", id);
		req.setAttribute("nombre", nombre);
		req.setAttribute("apellidos", apellidos);
		req.setAttribute("telefono", telefono);
		
		req.getRequestDispatcher("/WEB-INF/editar.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
}
