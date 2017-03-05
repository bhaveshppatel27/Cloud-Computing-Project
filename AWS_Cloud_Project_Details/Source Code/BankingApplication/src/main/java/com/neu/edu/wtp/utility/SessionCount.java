package com.neu.edu.wtp.utility;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SessionCount implements HttpSessionListener {

     private static int totalActiveSessions;

     public int getTotalActiveSession(){
           return totalActiveSessions;
     }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
//           totalActiveSessions++;
//           System.out.println("sessionCreated - add one session into counter");
//           printCounter(arg0);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
//           totalActiveSessions--;
//           System.out.println("sessionDestroyed - deduct one session from counter");
//           printCounter(arg0);
    }

    
    private void printCounter(HttpSessionEvent sessionEvent){

//          HttpSession session = sessionEvent.getSession();
//
////          ApplicationContext ctx =
////                WebApplicationContextUtils.
////                      getWebApplicationContext(session.getServletContext());
//          session.setAttribute("session-count", totalActiveSessions);
//
////          CounterService counterService =
////                      (CounterService) ctx.getBean("counterService");
//
////          counterService.printCounter(totalActiveSessions);
    }
}