package servlets.customerServlets;

import databaseOperations.CustomerOperations;
import model.Customer;
import passwordHandlers.PasswordHandler;
import validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginCustomerServlet", urlPatterns = {"/customer/login"})
public class LoginCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isFormProperlyFilled = true;
        if (!Validator.isUsernameValid(request.getParameter("username"))) {
            isFormProperlyFilled = false;
            request.setAttribute("usernameError", "The username should be between 6 and 64 characters" +
                    " and should consist only of latin letters, 0-9 and underscores.");
        }
        if (!Validator.isPasswordValid(request.getParameter("password"))) {
            isFormProperlyFilled = false;
            request.setAttribute("passwordError", "The password should be between 8 and 64 characters" +
                    " and should consist only of latin letters, 0-9 and underscores.");
        }
        if (!isFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/customer/login.jsp").forward(request, response);
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer customer = CustomerOperations.getCustomerByUsername(username);
        if (customer == null) {
            request.setAttribute("usernameError", "There is not a user with such username.");
            getServletContext().getRequestDispatcher("/customer/login.jsp").forward(request, response);
        } else {
            String hashedPassword = PasswordHandler.hashPassword(password, customer.getSalt());
            if (!customer.getHashedPassword().equals(hashedPassword)) {
                request.setAttribute("passwordError", "Wrong password!");
                getServletContext().getRequestDispatcher("/customer/login.jsp").forward(request, response);
            } else {
                HttpSession httpSession = request.getSession();
                httpSession.setMaxInactiveInterval(1800);
                httpSession.setAttribute("customerLogged", "true");
                httpSession.setAttribute("customerUsername", customer.getUsername());
                httpSession.setAttribute("firstName", customer.getFirstName());
                httpSession.setAttribute("lastName", customer.getLastName());
                httpSession.setAttribute("customerId", customer.getId());
                response.sendRedirect(request.getContextPath() + "/customer/logged/main_page");
            }
        }
    }
}
