package servlets.customerServlets;

import databaseOperations.CustomerOperations;
import factory.CustomerFactory;
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

@WebServlet(name = "RegisterCustomerServlet", urlPatterns = {"/customer/register"})
public class RegisterCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * TODO:
         * pathToRedirect, just to be able to use this Servlet also for the case when admin is creating the stuff. :)
         */
        String pathToRedirect = request.getParameter("pathToRedirect");
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
        if (!request.getParameter("password").equals(request.getParameter("confPassword"))) {
            isFormProperlyFilled = false;
            request.setAttribute("passwordError", "Passwords must match.");
        }
        if (!Validator.isEmailValid(request.getParameter("email"))) {
            isFormProperlyFilled = false;
            request.setAttribute("emailError", "Invalid email address.");
        }
        if (!Validator.isNameValid(request.getParameter("firstName"))) {
            isFormProperlyFilled = false;
            request.setAttribute("firstNameError", "First name cannot be empty.");
        }
        if (!Validator.isNameValid(request.getParameter("lastName"))) {
            isFormProperlyFilled = false;
            request.setAttribute("lastNameError", "Last name cannot be empty.");
        }
        if (!Validator.isNameValid(request.getParameter("companyName"))) {
            isFormProperlyFilled = false;
            request.setAttribute("companyNameError", "Company name cannot be empty.");
        }
        if (!isFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/customer/register.jsp").forward(request, response);
        }
        String username = request.getParameter("username");
        boolean errorsInInformation = false;
        if (CustomerOperations.getCustomerByUsername(username) != null) {
            errorsInInformation = true;
            request.setAttribute("usernameError", "This username is already used.");
            getServletContext().getRequestDispatcher("/customer/register.jsp").forward(request, response);
        }
        String companyName = request.getParameter("companyName");
        if (CustomerOperations.getCustomerByCompanyName(companyName) != null) {
            errorsInInformation = true;
            request.setAttribute("companyNameError", "This company already has a representative.");
            getServletContext().getRequestDispatcher("/customer/register.jsp").forward(request, response);
        }
        if (!errorsInInformation) {
            String password = request.getParameter("password");
            String salt = PasswordHandler.generateSalt();
            request.setAttribute("salt", salt);
            request.setAttribute("hashedPassword", PasswordHandler.hashPassword(password, salt));
            Customer customer = CustomerFactory.createCustomerFromForm(request);
            CustomerOperations.insertCustomer(customer);
            HttpSession httpSession = request.getSession();
            httpSession.setMaxInactiveInterval(1800);
            httpSession.setAttribute("customerLogged", "true");
            httpSession.setAttribute("customerUsername", customer.getUsername());
            httpSession.setAttribute("firstName", customer.getFirstName());
            httpSession.setAttribute("lastName", customer.getLastName());
            int customerId = CustomerOperations.getCustomerByUsername(customer.getUsername()).getId();
            httpSession.setAttribute("customerId",customerId);
            response.sendRedirect(request.getContextPath() + "/customer/logged/main_page");
        }
    }
}
