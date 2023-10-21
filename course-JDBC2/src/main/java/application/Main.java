package application;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import database.DatabaseConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

//Get data from the database:
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery("select * from department");) {
//
//            while (rs.next()) {
//                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


//Insert data from the database:
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement st = connection.prepareStatement(
//                     "INSERT INTO seller (Name, Email, BirthDate, BaseSalary,DepartmentId) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
//        ) {
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//            st.setString(1, "Robert Gleison");
//            st.setString(2, "robert@gmail.com");
//            LocalDate birthdate = LocalDate.parse("23/03/2001", dtf);
//            st.setDate(3, java.sql.Date.valueOf(birthdate));
//            st.setDouble(4, 3000.0);
//            st.setInt(5, 4);
//
//            int rowsAffected = st.executeUpdate();
//            if (rowsAffected > 0) {
//                ResultSet rs = st.getGeneratedKeys();
//                while (rs.next()) {
//                    int id = rs.getInt(1);
//                    System.out.println("Done! ID = " + id);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


//Update data from the database:
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement st = connection.prepareStatement(
//                     "UPDATE seller SET BaseSalary = BaseSalary + ?"
//                             + "WHERE (DepartmentID = ?)");
//            ) {
//            st.setDouble(1, 5000.0);
//            st.setInt(2, 2);
//            int rowsAffected = st.executeUpdate();
//            System.out.println("Done! Rows affected; " + rowsAffected);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


//Delete data from the database:
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement st = connection.prepareStatement(
//                     "DELETE FROM department WHERE id=?");
//        ) {
//            st.setInt(1,2);
//            int rowsAffected = st.executeUpdate();
//            System.out.println("Done! Rows affected; " + rowsAffected);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
