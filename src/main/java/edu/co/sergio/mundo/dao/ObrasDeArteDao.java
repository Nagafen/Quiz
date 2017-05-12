package edu.co.sergio.mundo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.co.sergio.mundo.vo.ObraArte;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Isabel-Fabian
 * @since 12/08/2015
 * @version 2
 * Clase que permite la gestion de la tabla Depto en la base de datos.
 * 
 * CREATE TABLE Depto(
 *     id_depto integer,
 *     nom_depto varchar(40),
 *     PRIMARY KEY(id_depto)
 * );
 */
 

public class ObrasDeArteDao{
	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<ObraArte> findAll() {
            List<ObraArte> obras= null;
	    String query = "SELECT * FROM Obra_de_Arte";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ObrasDeArteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    String nombreO = null;
            String descripcion = null;
            String estilo = null;
            int valor = 0;
	    String nombreA = null;
	
	    while (rs.next()){
	    	if(obras == null){
	    		obras= new ArrayList<ObraArte>();
	    	}
	      
	        ObraArte obra= new ObraArte();
	        nombreO = rs.getString("nombreO");
	        obra.setNombreO(nombreO);
	        
	        descripcion = rs.getString("descripcion");
	        obra.setDescripcion(descripcion);
                
                estilo = rs.getString("estilo");
                obra.setEstilo(estilo);
                
                valor = rs.getInt("valor");
                obra.setValor(valor);
                
                nombreA = rs.getString("nombre");
                obra.setNombreA(nombreA);
	        
	        obras.add(obra);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Departamentos");
			e.printStackTrace();
		}
	    
	    return obras;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param ObraArte recibe un objeto de tipo ObraArte 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean InsertarObraDeArte(ObraArte O) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ObrasDeArteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = "insert into Obra_de_Arte (nombreO,descripcion,estilo,valor,nombre)"  + " values (?,?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString (1, O.getNombreO());
                        preparedStmt.setString (2, O.getDescripcion());
                        preparedStmt.setString (3, O.getEstilo());
                        preparedStmt.setInt (4, O.getValor());
                        preparedStmt.setString (5, O.getNombreA());
                 
			result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
