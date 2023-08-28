package TA31.database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.logging.Level;


public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/TA31";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
    	Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Server Connected");
            }
            DatabaseConnector dbConnector = new DatabaseConnector();

            dbConnector.createDB("TA31");

            createTable("TA31");
            
            insertData("TA31");
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void createDB(String name) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String Query = "CREATE DATABASE IF NOT EXISTS " + name;
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null,  "Se ha creado la base de datos " + name + " de forma exitosa.");    		
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // METODO QUE CREA TABLAS MYSQL
    public static void createTable(String db) {
        Connection connection = null;
    	try {
    		connection = DriverManager.getConnection(URL, USER, PASSWORD);
    		String Querydb = "USE " +db+ ";";
    		Statement stdb = connection.createStatement();
    		stdb.executeUpdate(Querydb);
    		
    		String Query1 = "CREATE TABLE IF NOT EXISTS FABRICANTES "
    				+ "(CODIGO INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(50))";
    		Statement st = connection.createStatement();
    		st.executeUpdate(Query1);
    		System.out.println("¡Tabla FABRICANTES creada con éxito!");
    		String Query2 = "CREATE TABLE IF NOT EXISTS ARTICULOS "
    				+ "(CODIGO INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(50), Precio INT, Fabricante INT, FOREIGN KEY(Fabricante) REFERENCES FABRICANTES(CODIGO))";
    		st = connection.createStatement();
    		st.executeUpdate(Query2);
    		System.out.println("¡Tabla ARTICULOS creada con éxito!");
    		
    	} catch (SQLException ex){
    		System.out.println(ex.getMessage());
    		System.out.println("Error creando tabla.");
    	}
    }
    
    // METODO QUE INSERTA DATOS EN TABLAS MYSQL
    public static void insertData(String db) {
        Connection connection = null;
    	try {
    		connection = DriverManager.getConnection(URL, USER, PASSWORD);
    		String Querydb = "USE " +db+";";
    		Statement stdb = connection.createStatement();
    		stdb.executeUpdate(Querydb);
    		
    		String Query = "INSERT INTO FABRICANTES(Nombre) VALUES(\"Fabricante1\"), (\"Fabricante2\"), (\"Fabricante3\"), (\"Fabricante4\"), (\"Fabricante5\")";
    		Statement st = connection.createStatement();
    		st.executeUpdate(Query);
    		System.out.println("Datos en FABRICANTES almacenados correctamente");
    		
    		String Query2 = "INSERT INTO ARTICULOS(Nombre, Precio, Fabricante) VALUES(\"Articulo1\", 100, 1), (\"Articulo2\", 150, 2), (\"Articulo3\", 200, 3), (\"Articulo4\", 250, 4), (\"Articulo5\", 300, 5)";
    		st = connection.createStatement();
    		st.executeUpdate(Query2);
    		
    		System.out.println("Datos EN ARTICULOS almacenados correctamente");
    		
    	} catch(SQLException ex ) {
    		System.out.println(ex.getMessage());
    		JOptionPane.showMessageDialog(null, "Error en el almacenamiento.");
    	}
    }
    
//    // METODO QUE OBTIENE VALORES MYSQL
//    public static void getValues(String db, String table_name) {
//        Connection connection = null;
//    	try {
//    		connection = DriverManager.getConnection(URL, USER, PASSWORD);
//    		String Querydb = "USE " +db+";";
//    		Statement stdb = connection.createStatement();
//    		stdb.executeUpdate(Querydb);
//    		
//    		String Query = "SELECT * FROM " + table_name;
//    		Statement st = connection.createStatement();
//    		java.sql.ResultSet resultSet;
//    		resultSet = st.executeQuery(Query);
//    		
//    		while (resultSet.next()) {
//    			System.out.println("ID: " + resultSet.getString("ID") + " "
//    			+ "Nombre: " + resultSet.getString("Nombre") + " "
//    			+ "Apellido: " + resultSet.getString("Apellido") + " "
//    			+ "Edad: " + resultSet.getString("Edad") + " "
//    			+ "Sexo: " + resultSet.getString("Sexo") + " "
//    		);
//    		}
//    	}catch (SQLException ex) {
//    		System.out.println(ex.getMessage());
//    		System.out.println("Error en la adquisición de datos.");
//    	}
//    }
//
//    
//    // METODO QUE ELIMINA VALORES DE NUESTRA BASE DE DATOS
//    public void deleteRecord(String table_name, String ID) {
//        Connection connection = null;
//    	try {
//    		connection = DriverManager.getConnection(URL, USER, PASSWORD);
//    		String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
//    		Statement st = connection.createStatement();
//    		st.executeUpdate(Query);
//    	} catch (SQLException ex) {
//    		System.out.println(ex.getMessage());
//    		JOptionPane.showMessageDialog(null, "Error borrando el registro especificado.");
//    	}
//    }
    
}