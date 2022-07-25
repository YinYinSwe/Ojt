package com.inputoutputstream.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost/admin";
        String uname = "root";
        String pass = "root";
 
        // Query from the database taken
        // Custom query taken to illustrate
        String query
            = "select username from student1 where id=6";
 
        // Loading and registering drivers
        Class.forName("com.mysql.jdbc.Driver");
 
        // Creating an connection object by
        // getConnection() method which is static method and
        // returns the instance of Connection class
 
        // This method takes 3 parameters as defined above
        Connection con
            = DriverManager.getConnection(url, uname, pass);
 
        Statement st=con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String name = rs.getString("username");
 
        // Lastly print the data
        System.out.println(name);
 
        // It is  good practice to clsoe the connection
        // using close() method
 
        // Closing the statement first
         st.close();
       
        // Now close the connection also
        con.close();
    }

    
}



