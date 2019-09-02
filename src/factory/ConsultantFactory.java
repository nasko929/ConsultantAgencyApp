package factory;

import model.Consultant;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

public class ConsultantFactory {

    public static Consultant createConsultantFromForm(HttpServletRequest request) {
        Consultant consultant = new Consultant();
        String username = request.getParameter("username");
        String hashedPassword = (String)request.getAttribute("hashedPassword");
        String salt = (String)request.getAttribute("salt");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int hourlyWage = Integer.parseInt(request.getParameter("hourlyWage"));
        String role = request.getParameter("role");
        Integer rank = Integer.parseInt(request.getParameter("rank"));
        consultant.setUsername(username);
        consultant.setHashedPassword(hashedPassword);
        consultant.setSalt(salt);
        consultant.setEmail(email);
        consultant.setFirstName(firstName);
        consultant.setLastName(lastName);
        consultant.setHourlyWage(hourlyWage);
        consultant.setRole(role);
        consultant.setRank(rank);
        return consultant;
    }

    public static Consultant createConsultantFromResultSet(ResultSet resultSet) throws SQLException {
        Consultant consultant = new Consultant();
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String hashedPassword = resultSet.getString("encrypted_password");
        String salt = resultSet.getString("salt");
        String email = resultSet.getString("email");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int hourlyWage = resultSet.getInt("hourly_wage");
        String role = resultSet.getString("role");
        Integer rank = resultSet.getInt("rank");
        consultant.setId(id);
        consultant.setUsername(username);
        consultant.setHashedPassword(hashedPassword);
        consultant.setSalt(salt);
        consultant.setEmail(email);
        consultant.setFirstName(firstName);
        consultant.setLastName(lastName);
        consultant.setHourlyWage(hourlyWage);
        consultant.setRole(role);
        consultant.setRank(rank);
        return consultant;
    }

}
