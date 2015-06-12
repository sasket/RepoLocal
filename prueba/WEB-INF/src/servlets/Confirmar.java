package servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BaseDatos;

@WebServlet(name = "Confirmar", urlPatterns = {"/confirmar.do"})
public class Confirmar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String sql = "select * from usuarios where id = ?";
		BaseDatos bd = new BaseDatos();
		bd.conectar();
		bd.PreparedStatement(sql);
		bd.setInt(1, Integer.parseInt(id));
		ResultSet rs = bd.executeQuery();
		StringBuffer sb = new StringBuffer();
		try {
			rs.next();
			sb.append(rs.getString("nombre"));
			sb.append("&nbsp;");
			sb.append(rs.getString("apellidos"));
			sb.append("&nbsp;");
			sb.append(rs.getString("telefono"));
			sb.append("&nbsp;");
		} catch (Exception e) {
			e.printStackTrace();
		}
		bd.close();
		req.setAttribute("datos", sb.toString());
		req.setAttribute("id", id);
		req.getRequestDispatcher("/WEB-INF/confirmar.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
}
