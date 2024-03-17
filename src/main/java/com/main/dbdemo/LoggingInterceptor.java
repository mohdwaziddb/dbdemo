package com.main.dbdemo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Log the incoming request
        String requestURL = request.getRequestURI().toString();
        //String domain = requestURL.split("/")[2];
        System.out.println("Incoming request: " + request.getRequestURI());
        return true; // Continue with request handling
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // Nothing to do here for logging
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // Log the response status
        System.out.println("Response status: " + response.getStatus());
    }
}
