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
    private static Connection conn = null;
    private static Statement stmt = null;
    
    /*public static void main(String[] args)
    {
        createConnection();
        System.out.println(countRegistros());
        //insertRegistro("Victor");
        //insertContacto("Susy1", "Tabata2"); 
        //System.out.println(AllContacts("Susy1")); 
        //System.out.println(login("Tabata2")); 
        System.out.println(name("Susy1"));
        shutdown();
    }*/
    
    /*
    Método que crea la conexión a la base de datos 
    */
    public static void createConnection()
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
    public static void shutdown()
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
     public static int countRegistros(){
        int total = 0; 
        try {
            stmt = conn.createStatement();
            ResultSet resultado = stmt.executeQuery("select count(*) from REGISTRO");
            if(resultado.next()){
                total = resultado.getInt(1) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
     /*
     Método que inserta un nuevo Registro y utiliza el método countRegistro() para crear el id del nuevo registro
     */
    public static void insertRegistro(String nombre)
    {
        try
        {
            int secuencia = countRegistros() +1; 
            String id= nombre + secuencia; 
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO REGISTRO (id, nombre) VALUES ('"+
                    id + "','" + nombre + "')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    /*
     Método que inserta un nuevo CONTACTO 
     */
    public static void insertContacto(String id_registro, String id_contacto)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO CONTACTOS (id_registro, id_contacto) VALUES ('"+
                    id_registro + "','" + id_contacto + "')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    
    /*
        Regresa si existe el en los registros este id 
     */
    public static boolean login (String id){
        boolean bandera = false; 
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from Registro where ID = '"+ id + "'");
             
            if(results.next()){
                 if(results.getString(1)!= null){
                     bandera = true; 
                 }
            }
            results.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera; 
    }
    
    /**/
    public static String AllContacts (String id){
        StringBuilder str = new StringBuilder();
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from CONTACTOS where id_registro = '"+ id + "'");
            while(results.next()){
                 str.append(results.getString(3));
                 str.append("\n");
            }
            results.close();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str.toString();
    }
    
    /**/
    public static String name (String id){
        StringBuilder str = new StringBuilder();
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select nombre from REGISTRO where id = '"+ id + "'");
            while(results.next()){
                 str.append(results.getString(1));
                 str.append("\n");
            }
            results.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str.toString();
    }
}
