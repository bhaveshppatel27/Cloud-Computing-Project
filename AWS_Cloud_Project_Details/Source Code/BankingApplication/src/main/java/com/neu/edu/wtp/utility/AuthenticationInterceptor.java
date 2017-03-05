package com.neu.edu.wtp.utility;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthenticationInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		//RequestDispatcher requestDispatcher;
//		System.out.println("Session------------------>"+session);
//		System.out.println("Session ID--------------->"+session.getId());
//		System.out.println((String)session.getAttribute("user") + " - "+ req.getParameter("userName"));
//		System.out.println((String)session.getAttribute("userName") + "Time :"+ new Date(System.currentTimeMillis()));
		if(session.getAttribute("user")==null){
			
			return true;
		}else
		{
			//req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, res);
			//return false;
		}
		return false;
	}

}
