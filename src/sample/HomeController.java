package sample;

import java.net.URL;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

import java.sql.ResultSet;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable
{

    @FXML
    private ComboBox<KeyValuePair> cbCustomerList;



    @Override
    public void initialize(final URL url, final ResourceBundle rb)
    {

    }



    @FXML
    private void getCustomerButton(ActionEvent event) throws IOException
    {

        //get MYSQL Connection
        MySQLConnection myclass =new MySQLConnection();
        Connection conn = myclass.getConnection();

        //make Query
        MySQLQuery customerQuery = new MySQLQuery();
        ResultSet customers = customerQuery.QueryCustomerList(conn);

        try
        {
            ResultSetMetaData rsmd = customers.getMetaData();

            //populate Combor Box with list of customers and customer numbers
            //each row
            while (customers.next())
            {
                String customername = customers.getString(1);
                String customernumber = customers.getString(2);
                cbCustomerList.getItems().add(new KeyValuePair(customernumber,customername));
            }

            conn.close();
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    };


    @FXML
    private void oncbCustomerListSelection()throws IOException
    {
        String id = cbCustomerList.getValue().getKey();
        //get MYSQL Connection
        MySQLConnection myclass =new MySQLConnection();
        Connection conn = myclass.getConnection();

        //make Query
        MySQLQuery customerQuery = new MySQLQuery();
        ResultSet customerforecastresults  = customerQuery.QueryCustomerForecast(conn,id);

        try
        {
            ResultSetMetaData rsmd = customerforecastresults.getMetaData();

              while (customerforecastresults.next())
            {
                String customernumber = customerforecastresults.getString(1);
                String customername = customerforecastresults.getString(2);
                System.out.println(customernumber);
            }

            conn.close();
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    };







}