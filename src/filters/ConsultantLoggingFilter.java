package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ConsultantLoggingFilter", urlPatterns = {"/consultant/*"})
public class ConsultantLoggingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession != null && httpSession.getAttribute("customerUsername") != null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()
                    + "/customer/logged/main_page");
        } else if (httpServletRequest.getRequestURI().contains("/logged/")) {
            if (httpSession == null || httpSession.getAttribute("consultantUsername") == null) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/consultant/login.jsp");
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            if (httpSession != null && httpSession.getAttribute("consultantUsername") != null &&
                    !httpServletRequest.getRequestURI().endsWith("/consultant/logout")) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()
                        + "/consultant/logged/main_page");
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
