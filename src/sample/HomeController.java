package sample;

import java.net.URL;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;

import java.sql.ResultSet;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable
{

    @FXML
    private ComboBox<KeyValuePair> cbCustomerList;
    @FXML
    private TableView forecastTable;

    @FXML
    private SplitPane MainSplitPane;


    @Override
    public void initialize(final URL url, final ResourceBundle rb)
    {
        //clear the 2 default columns that appear in TableView
        forecastTable.getColumns().clear();

    }



    @FXML
    private void getCustomerButton(ActionEvent event) throws IOException
    {
        MainSplitPane.setDividerPosition(0,0.1);

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

        forecastTable.setEditable(true);

        TableColumn colCustomername = new TableColumn("Customer Name");
        TableColumn colSku = new TableColumn("Sku");
        TableColumn colDescription = new TableColumn("Description");
        TableColumn colForecast1 = new TableColumn("M1 forecast");
        TableColumn colForecast2 = new TableColumn("M2 forecast");
        TableColumn colForecast3 = new TableColumn("M3 forecast");
        TableColumn colForecast4 = new TableColumn("M4 forecast");
        TableColumn colForecast5 = new TableColumn("M5 forecast");
        TableColumn colForecast6 = new TableColumn("M6 forecast");

        forecastTable.getColumns()
                .addAll(colCustomername,colSku,colDescription,colForecast1,colForecast2,colForecast3,colForecast4,colForecast5,colForecast6);

        try
        {
            ResultSetMetaData rsmd = customerforecastresults.getMetaData();

              while (customerforecastresults.next())
            {
                String customernumber = customerforecastresults.getString(1);


            }

            conn.close();
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    };







}