package factory;

import model.Customer;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFactory {
    public static Customer createCustomerFromForm(HttpServletRequest request) {
        Customer customer = new Customer();
        String username = request.getParameter("username");
        String hashedPassword = (String)request.getAttribute("hashedPassword");
        String salt = (String)request.getAttribute("salt");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String companyName = request.getParameter("companyName");
        customer.setUsername(username);
        customer.setHashedPassword(hashedPassword);
        customer.setSalt(salt);
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCompanyName(companyName);
        return customer;
    }

    public static Customer createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String hashedPassword = resultSet.getString("encrypted_password");
        String salt = resultSet.getString("salt");
        String email = resultSet.getString("email");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String companyName = resultSet.getString("company_name");
        customer.setId(id);
        customer.setUsername(username);
        customer.setHashedPassword(hashedPassword);
        customer.setSalt(salt);
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCompanyName(companyName);
        return customer;
    }
}
