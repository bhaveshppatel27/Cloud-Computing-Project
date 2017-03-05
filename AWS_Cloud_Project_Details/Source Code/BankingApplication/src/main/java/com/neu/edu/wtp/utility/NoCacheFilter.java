package com.neu.edu.wtp.utility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//This filter is used to clear cache so that user cannot see the account page by pressing back button
//once logged out and the session is invalidated.
@WebFilter(value = "/*", description = "Session Checker Filter")
public class NoCacheFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//System.out.println("In------------------------------>");
//		 	HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        response.setDateHeader("Expires", 0); // Proxies.

//		if (!request.getRequestURI().endsWith("signIn.jsp") &&
//                request.getSession().getAttribute("AUTHENTICATED") == null) {
//            response.sendRedirect(request.getContextPath() + "/home.jsp");
//        }
	        chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
