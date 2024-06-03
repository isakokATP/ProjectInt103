package int103.Connector;

import java.sql.*;

public class ConnectionDB {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //database
        String jdbc = "jdbc:mysql://localhost:3306/?user=int103";
        String username = "int103";
        String password = "int103";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //load mysql jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Establish the connection
            connection = DriverManager.getConnection(jdbc, username, password);
            System.out.println("Connected to the database successfully");

            //create statement
            statement = connection.createStatement();

            //Execute a query
            String sql = "SELECT id, name FROM int103";
            resultSet = statement.executeQuery(sql);

            //Process the result
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID :" + id + ",Name: " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Cant connect to your DB" + e.getMessage());
        }
    }
}