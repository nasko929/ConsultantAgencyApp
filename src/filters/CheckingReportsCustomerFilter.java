package filters;

import databaseOperations.ReportOperations;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CheckingReportsCustomerFilter", urlPatterns = {"/customer/logged/check_report"})
public class CheckingReportsCustomerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession httpSession = request.getSession(false);
        if(httpSession == null) {
            response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
            return;
        }
        int customerId = (int) httpSession.getAttribute("customerId");
        String requestedReportId = request.getParameter("reportId");
        if(requestedReportId == null || !requestedReportId.matches("^[0-9]+$")) {
            response.sendRedirect(request.getContextPath() + "/customer/logged/main_page");
        } else {
            int reportId = Integer.parseInt(requestedReportId);
            if(ReportOperations.getReportWithReportIdAndCustomerId(reportId,customerId) == null) {
                response.sendRedirect(request.getContextPath() + "/customer/logged/main_page");
            } else {
                chain.doFilter(req,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
