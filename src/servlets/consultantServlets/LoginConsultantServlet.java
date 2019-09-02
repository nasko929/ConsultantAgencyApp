package servlets.consultantServlets;

import databaseOperations.ConsultantOperations;
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

@WebServlet(name = "LoginConsultantServlet", urlPatterns = {"/consultant/login"})
public class LoginConsultantServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
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
            getServletContext().getRequestDispatcher("/consultant/login.jsp").forward(request, response);
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Consultant consultant = ConsultantOperations.getConsultantByUniqueField(username);
        if(consultant == null) {
            request.setAttribute("usernameError","There is not a user with such username");
            getServletContext().getRequestDispatcher("/consultant/login.jsp").forward(request,response);
        } else {
            String hashedPassword = PasswordHandler.hashPassword(password,consultant.getSalt());
            if(!consultant.getHashedPassword().equals(hashedPassword)) {
                request.setAttribute("passwordError","Wrong password!");
                getServletContext().getRequestDispatcher("/consultant/login.jsp").forward(request,response);
            } else {
                HttpSession httpSession = request.getSession();
                httpSession.setMaxInactiveInterval(1800);
                if(consultant.getRole().equals("Admin")) {
                    httpSession.setAttribute("adminLogged","true");
                    httpSession.setAttribute("adminUsername", consultant.getUsername());
                    response.sendRedirect(request.getContextPath() + "/admin/main_page.jsp");
                } else {
                    httpSession.setAttribute("consultantLogged", "true");
                    httpSession.setAttribute("consultantUsername", consultant.getUsername());
                    httpSession.setAttribute("firstName", consultant.getFirstName());
                    httpSession.setAttribute("lastName", consultant.getLastName());
                    httpSession.setAttribute("consultantId", consultant.getId());
                    httpSession.setAttribute("filterAccepted", "false");
                    response.sendRedirect(request.getContextPath() + "/consultant/logged/main_page");
                }
            }
        }
    }
}
