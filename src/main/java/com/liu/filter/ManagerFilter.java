package com.liu.filter;

import com.liu.domain.Manager;
import com.mysql.cj.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ManagerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpSession session= request.getSession();
        String requestURI = request.getRequestURI();
        Manager userInfo= (Manager) session.getAttribute("userInfo");
        System.out.println(requestURI);
        System.out.println(request.getRequestURL());
        if (requestURI.contains("/login.jsp")||"http://localhost:8080/managerSystem/".equals(request.getRequestURL())){
            chain.doFilter(req,resp);
            System.out.println("login.jsp");
        }else {
            if (userInfo!= null) {
                chain.doFilter(req, resp);
                System.out.println("access");
            } else {
                request.setAttribute("error", "sign in,please!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                System.out.println("error");
                return;

            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
