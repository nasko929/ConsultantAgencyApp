package databaseOperations;

import databaseAccess.DatabaseAccess;
import factory.ConsultantFactory;
import model.Consultant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultantOperations {

    public static void insertConsultant(Consultant consultant) {
        String prepStatementInsert = "INSERT INTO consultants (username,encrypted_password,salt,email,first_name," +
                "last_name,hourly_wage,`rank`) VALUES(?,?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementInsert);
            preparedStatement.setString(1,consultant.getUsername());
            preparedStatement.setString(2,consultant.getHashedPassword());
            preparedStatement.setString(3,consultant.getSalt());
            preparedStatement.setString(4,consultant.getEmail());
            preparedStatement.setString(5,consultant.getFirstName());
            preparedStatement.setString(6,consultant.getLastName());
            preparedStatement.setInt(7,consultant.getHourlyWage());
            preparedStatement.setInt(8,consultant.getRank());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Consultant getConsultantByUniqueField(String username) {
        String prepStatementFindById = "SELECT * FROM consultants WHERE username = ?";
        Consultant consultant = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementFindById);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                consultant = ConsultantFactory.createConsultantFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return consultant;
    }

    public static Consultant getConsultantByUniqueField(int id) {
        String prepStatementFindById = "SELECT * FROM consultants WHERE id = ?";
        Consultant consultant = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementFindById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                consultant = ConsultantFactory.createConsultantFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return consultant;
    }
}
