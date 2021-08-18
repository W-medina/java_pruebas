package com.java_pruebas.conexion;

import java.sql.*;
import java.util.ArrayList;
import com.google.gson.Gson;

public class Conexion {
	
	public Conexion() {
		
		try {
			//direccion y nombre de la base de datos 
			String url = "jdbc:sqlserver://192.168.50.14:1433;databaseName=PIDAMASTER2";
			
			//conexion con usuario y contraseñia
			conn = DriverManager.getConnection(url,"sa", "W1P0w1p0");
			
			//Statement
			st = conn.createStatement();
			
			//consulta SQL
			rs = st.executeQuery("SELECT usuario, contrasenha, persona_id FROM AU_Usuarios");
						
		}catch (SQLException e) {e.getLocalizedMessage();}
			
	}
	
	
	public ResultSet getRs() {

		//lista para iterar los datos que crearan el objeto json
		ArrayList<String> json = new ArrayList<String>();

		try {
				//Libreria para convecion a json
				Gson gson = new Gson();

				//Bucle que recorre el ResultSet de la consulta
				while(rs.next()) {
					
					//Clase modelo para la convercion a json 
					ConverJson javaJson = new ConverJson(this.rs.getString("usuario"), this.rs.getString("contrasenha"), this.rs.getString("persona_id"));
					
					//en cada iteracion se agrega un registro a la lista
					json.add(gson.toJson(javaJson));
				}
				
				//muestro por consola la lista completa como objeto JSON
				System.out.println(json);
				
		} catch (SQLException e) {	
			System.out.println("Problema loco!!!" + e.getLocalizedMessage());
		}

		return null;
	}

	
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	

}
