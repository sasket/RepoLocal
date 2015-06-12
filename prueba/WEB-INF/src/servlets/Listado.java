package servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BaseDatos;

@WebServlet(name = "Listado", urlPatterns = {"/listado.do"})
public class Listado extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();

		String buscar = req.getParameter("buscar");
		String filtro = (String) session.getAttribute("filtro");
		String modo = req.getParameter("modo");
		
		BaseDatos bd = new BaseDatos();
		bd.conectar();
		String sql;
		if ((modo != null && modo.equals("all")) || 
					(buscar == null && filtro == null)) {
			sql = "select * from usuarios";
			bd.PreparedStatement(sql);
		} else {
			if (buscar == null) {
				buscar = filtro;
			} else
				session.setAttribute("filtro", buscar);
			
			sql = "select * from usuarios where nombre like ? or apellidos like ?";
			bd.PreparedStatement(sql);
			bd.setString(1, "%" + buscar + "%");
			bd.setString(2, "%" + buscar + "%");
		}
		
		ResultSet rs = bd.executeQuery();
		StringBuffer sb = new StringBuffer();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String telefono = rs.getString("telefono");
				sb.append(nombre + "&nbsp;");
				sb.append(apellidos + "&nbsp;");
				sb.append(telefono + "&nbsp;");
				sb.append("<a href='confirmar.do?id=");
				sb.append(id + "'>Eliminar</a>");
				sb.append("&nbsp;");
				sb.append("<a href='editar.do?id=");
				sb.append(id + "'>Editar</a>");
				sb.append("<br/>");
			}
		} catch (Exception e) {
			sb.append("Error en base de datos");
		}
		bd.close();
		req.setAttribute("lista", sb.toString());
		req.getRequestDispatcher("/WEB-INF/listado.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
	
}
