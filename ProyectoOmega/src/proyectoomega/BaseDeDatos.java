package proyectoomega;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDeDatos {
    
    private static String dbURL = "jdbc:derby://localhost:1527/omega;create=true;user=omega;password=omega";
    private static String tableName = "REGISTRO";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public static void main(String[] args)
    {
        createConnection();
        System.out.println(countRegistros());
        insertRegistro("Susy");
        //selectRestaurants();
        shutdown();
    }
    
    /*
    Método que crea la conexión a la base de datos 
    */
     private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL,"omega", "omega"); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
     
     /*
     Método que termina la conexión a la base de datos
     */
    private static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }
    }
    
    /*
    Método que cuenta la cantidad de Rows que hay en la tabla REGISTRO 
    */
     private static int countRegistros(){
        int total = 0; 
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            if(results.next()){
                total++; 
            }  
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
     /*
     Método que inserta un nuevo Registro y utiliza el método countRegistro() para crear el id del nuevo registro
     */
    private static void insertRegistro(String nombre)
    {
        try
        {
            int secuencia = countRegistros() +1; 
            String id= nombre + secuencia; 
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO " + tableName + "(id, nombre) VALUES ('"+
                    id + "','" + nombre + "')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
}
