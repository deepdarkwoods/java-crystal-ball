package sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MySQLQuery
{

    Statement stmt = null;
    ResultSet rs = null;
    String query = null;

    public ResultSet QueryCustomerList(Connection conn)
    {

        query = "SELECT DISTINCT customername,customernumber FROM customerforecast ORDER BY customername";

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException ex)
        {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return rs;
    }


    public ResultSet QueryCustomerForecast(Connection conn,String customernumber)
    {
        query = "SELECT " +
                "customerforecast.customernumber,customerforecast.customername,customerforecast.sku,customerforecast.skudescription," +
                "sh.shipJAN2017, sh.shipFEB2017, sh.shipMAR2017, sh.shipAPR2017, sh.shipMAY2017, sh.shipJUN2017," +
                "sh.shipJUL2017,sh.shipAUG2017,sh.shipSEP2017,"+
                "SUM(CASE WHEN customerforecast.period='2017-09-30' then customerforecast.quantity else 0 end) as 'SEP2017',"+
                "SUM(CASE WHEN customerforecast.period='2017-10-28' then customerforecast.quantity else 0 end) as 'OCT2017',"+
                "SUM(CASE WHEN customerforecast.period='2017-11-25' then customerforecast.quantity else 0 end) as 'NOV2017',"+
                "SUM(CASE WHEN customerforecast.period='2017-12-31' then customerforecast.quantity else 0 end) as 'DEC2017',"+
                "SUM(CASE WHEN customerforecast.period='2018-01-27' then customerforecast.quantity else 0 end) as 'JAN2018',"+
                "SUM(CASE WHEN customerforecast.period='2018-02-24' then customerforecast.quantity else 0 end) as 'FEB2018' "+
                "FROM customerforecast "+
                "LEFT JOIN "+
                "(SELECT customernumber,sku,"+
                "SUM(CASE WHEN shiphistory.monthenddate='2017-09-30' then shiphistory.shipqty else 0 end) as 'shipSEP2017',"+
                "SUM(CASE WHEN shiphistory.monthenddate='2017-08-26' then shiphistory.shipqty else 0 end) as 'shipAUG2017',"+
                "SUM(CASE WHEN shiphistory.monthenddate='2017-07-29' then shiphistory.shipqty else 0 end) as 'shipJUL2017',"+
                "SUM(CASE WHEN shiphistory.monthenddate='2017-07-01' then shiphistory.shipqty else 0 end) as 'shipJUN2017',"+
                "SUM(CASE WHEN shiphistory.monthenddate='2017-05-27' then shiphistory.shipqty else 0 end) as 'shipMAY2017',"+
                "SUM(CASE WHEN shiphistory.monthenddate='2017-04-29' then shiphistory.shipqty else 0 end) as 'shipAPR2017',"+
                "SUM(CASE WHEN shiphistory.monthenddate='2017-04-01' then shiphistory.shipqty else 0 end) as 'shipMAR2017',"+
                "SUM(CASE WHEN shiphistory.monthenddate='2017-02-25' then shiphistory.shipqty else 0 end) as 'shipFEB2017',"+
                "SUM(CASE WHEN shiphistory.monthenddate='2017-01-28' then shiphistory.shipqty else 0 end) as 'shipJAN2017'"+
                "FROM shiphistory "+
                "GROUP BY shiphistory.customername,shiphistory.sku) as sh "+
                "ON customerforecast.customernumber = sh.customernumber AND customerforecast.sku = sh.sku "+
                "WHERE customerforecast.customernumber = " + customernumber + " " +
                "GROUP BY customerforecast.customername,customerforecast.sku";

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException ex)
        {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return rs;

    }




}
