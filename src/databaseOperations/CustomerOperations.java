package databaseOperations;

import databaseAccess.DatabaseAccess;
import factory.CustomerFactory;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerOperations {

    public static void insertCustomer(Customer customer) {
        String prepStatementInsert = "INSERT INTO customers (username,encrypted_password,salt,email,first_name," +
                "last_name,company_name) VALUES(?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementInsert);
            preparedStatement.setString(1,customer.getUsername());
            preparedStatement.setString(2,customer.getHashedPassword());
            preparedStatement.setString(3,customer.getSalt());
            preparedStatement.setString(4,customer.getEmail());
            preparedStatement.setString(5,customer.getFirstName());
            preparedStatement.setString(6,customer.getLastName());
            preparedStatement.setString(7,customer.getCompanyName());
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

    public static ArrayList<Customer> getAllCustomers() {
        String statementForGettingAllCustomers = "SELECT * FROM customers";
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseAccess.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(statementForGettingAllCustomers);
            while (resultSet.next()) {
                customers.add(CustomerFactory.createCustomerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    public static Customer getCustomerById(int id) {
        String prepStatementFindById = "SELECT * FROM customers WHERE id = ?";
        Customer customer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementFindById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer = CustomerFactory.createCustomerFromResultSet(resultSet);
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
        return customer;
    }

    public static Customer getCustomerByUsername(String username) {
        String prepStatementFindById = "SELECT * FROM customers WHERE username = ?";
        Customer customer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementFindById);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer = CustomerFactory.createCustomerFromResultSet(resultSet);
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
        return customer;
    }

    public static Customer getCustomerByCompanyName(String companyName) {
        String prepStatementFindById = "SELECT * FROM customers WHERE company_name = ?";
        Customer customer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementFindById);
            preparedStatement.setString(1, companyName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer = CustomerFactory.createCustomerFromResultSet(resultSet);
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
        return customer;
    }
}
