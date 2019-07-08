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
        System.out.println(request.getHeader("request"));
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURL());
        if (requestURI.contains("/login.jsp")||requestURI.contains("/gotoIndex")||requestURI.contains("/studentLogin.jsp")||requestURI.contains("/gotoMyController")){
            chain.doFilter(req,resp);
        }else {
            if (userInfo==null){
                request.setAttribute("error","sign in,please");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else {
                chain.doFilter(req,resp);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

}
