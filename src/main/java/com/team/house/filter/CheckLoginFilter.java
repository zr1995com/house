package com.team.house.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckLoginFilter  implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("你想进去吗");
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse)response;
        String url=req.getRequestURI();
        String path=url.substring(url.lastIndexOf("/")+1);
        if (!path.equals("guanli.jsp")){
            chain.doFilter(request,response);
        }else {
            HttpSession session=req.getSession();
            Object o=session.getAttribute("loginInfo");
            if(o==null){
                resp.sendRedirect("login.jsp");
            }else {
                chain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
