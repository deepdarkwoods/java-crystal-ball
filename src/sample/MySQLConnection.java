package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection
{



    public Connection getConnection()

    {
        String myDriver = null;
        String myUrl = null;
        Connection conn = null;
        try

        {
            // create a mysql database connection
            myDriver = "com.mysql.jdbc.Driver";
            myUrl = "jdbc:mysql://localhost/ball";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "root");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Class " + myDriver + " not found!");
        }

        return conn;
    }
}