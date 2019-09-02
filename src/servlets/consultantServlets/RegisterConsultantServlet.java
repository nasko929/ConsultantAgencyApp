package servlets.consultantServlets;

import databaseOperations.ConsultantOperations;
import factory.ConsultantFactory;
import model.Consultant;
import passwordHandlers.PasswordHandler;
import validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegisterConsultantServlet", urlPatterns = {"/consultant/register"})
public class RegisterConsultantServlet extends HttpServlet {
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
        if (!Validator.isNumberValid(request.getParameter("hourlyWage"))) {
            isFormProperlyFilled = false;
            request.setAttribute("hourlyWageError", "This field should contain a non-negative integer.");
        }
        if (!isFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/consultant/register.jsp").forward(request, response);
        }
        String username = request.getParameter("username");
        if (ConsultantOperations.getConsultantByUniqueField(username) != null) {
            request.setAttribute("usernameError", "This username is already used.");
            getServletContext().getRequestDispatcher("/consultant/register.jsp").forward(request, response);
        } else {
            String password = request.getParameter("password");
            String salt = PasswordHandler.generateSalt();
            request.setAttribute("salt", salt);
            request.setAttribute("hashedPassword", PasswordHandler.hashPassword(password, salt));
            Consultant consultant = ConsultantFactory.createConsultantFromForm(request);
            ConsultantOperations.insertConsultant(consultant);
            HttpSession httpSession = request.getSession();
            httpSession.setMaxInactiveInterval(1800);
            httpSession.setAttribute("consultantLogged","true");
            httpSession.setAttribute("consultantUsername",consultant.getUsername());
            httpSession.setAttribute("firstName", consultant.getFirstName());
            httpSession.setAttribute("lastName", consultant.getLastName());
            int consultantId = ConsultantOperations.getConsultantByUniqueField(consultant.getUsername()).getId();
            httpSession.setAttribute("consultantId", consultantId);
            httpSession.setAttribute("filterAccepted", "false");
            response.sendRedirect(request.getContextPath() + "/consultant/logged/main_page");
        }
    }
}
