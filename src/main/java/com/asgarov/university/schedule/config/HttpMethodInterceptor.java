package com.asgarov.university.schedule.config;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpMethodInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String theMethod = request.getMethod();

        return HttpMethod.GET.matches(theMethod) || HttpMethod.POST.matches(theMethod) ||
                HttpMethod.PUT.matches(theMethod) || HttpMethod.DELETE.matches(theMethod);
    }
}
