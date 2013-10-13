package cargakdd;

/**
 *
 * @author Ciriaco
 */
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Test_Conection {

    private Connection conexion = null;
    private String servidor = "localhost:3306";
    private String database = "dwh?zeroDateTimeBehavior=convertToNull";
    private String usuario = "root";
    private String password = "juan";
    private String url = "";

    public Test_Conection() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            url = "jdbc:mysql://" + servidor + "/" + database + "";
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion a Base de Datos " + url + " . . . . .Ok");

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public Connection cerrarConexion() {
        try {
            conexion.close();
            System.out.println("Cerrando conexion a " + url + " . . . . . Ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        conexion = null;
        return conexion;
    }

    public List consultarEmpresa(String cotizante) {

        List lista=new LinkedList();
        String sql_select = "SELECT Empresa FROM empresa_cotizante WHERE Cotizante='" + cotizante + "'";
        try {
            Statement sentencia = getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql_select);
           
            
            while (resultado.next()) {
                 String i = resultado.getString("Empresa");
                 lista.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        
            return lista;
        }
    }
