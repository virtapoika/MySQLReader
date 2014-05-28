package mysqlreader;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Atte Pohjanmaa 2014
 * virtapoika@gmail.com
 * Free to use!
 * 
 * USAGE:
 * String[] array = new MySQLReader(ip, database, user, pass, table, the wanted column).get();
 */
public class MySQLReader 
{
    
    static String ip;
    static String db;
    static String user;
    static String pass;
    static String table;
    static String wanted;
    
    
    static String[] arvo;
    

    public MySQLReader(String ip, String db, String user, String pass, String table, String wanted)
    {
        this.ip = ip;
        this.db = db;
        this.user = user;
        this.pass = pass;
        this.table = table;
        this.wanted = wanted;
    }
    public static String[] get()
    {
        try 
        {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://"+ip+"/"+db, user, pass);
            
            java.sql.Statement st = con.createStatement();
            String sql = ("SELECT * FROM "+table);
            ResultSet rs = st.executeQuery(sql);
            
            arvo = new String[999999];
            
            System.out.println("-----------------------------------------");
            
            
            
            int i = 0;
            
            while(true)
            {
                if(rs.next()) 
                {
                    arvo[i] = (rs.getString(wanted));
                    i++;
                }
                else if(!rs.next())
                {          
                    break;
                }
                
            }
             
            con.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL driver is missing!");
        } catch (InstantiationException ex) {
            System.out.println("Instantiation error");
        } catch (IllegalAccessException ex) {
            System.out.println("Login error, check user/pass");
        } catch (SQLException ex) {
            System.out.println("SQL error");
            ex.printStackTrace();
        }
        return arvo;
    }
    
}
