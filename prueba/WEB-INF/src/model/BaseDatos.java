package model;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDatos implements Closeable{

	private String user = "root";
	private String pass = "1234";
	private String cadena = "jdbc:mysql://127.0.0.1/clientes";
	
	Connection con;
	PreparedStatement pstm;
	
	public void conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(cadena, user, pass);
		} catch (Exception e) {
			System.out.println("Error al conectar con la base de datos");
			e.printStackTrace();
		}
		
	}
	
	public void close() {
		try {
			pstm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void PreparedStatement(String sql) {
		try {
			pstm = con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setString(int n, String s) {
		try {
			pstm.setString(n, s);
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setInt(int n, int i) {
		try {
			pstm.setInt(n, i);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet executeQuery() {
		try {
			return pstm.executeQuery();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int executeUpdate() {
		try {
			return pstm.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	
}
