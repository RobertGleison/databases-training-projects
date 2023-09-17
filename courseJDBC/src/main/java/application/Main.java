package application;


import java.sql.*;

/* 1- import sql package
*  2- load and register driver (connector)
*  3- create connection
*  4- create statement
*  5- execute query
*  6- process result
*  7- close
* */
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/courseJDBC";
        String user = "root";
        String password = "";
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("");


            st.close();
            connection.close();
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());;
        }
    }
}