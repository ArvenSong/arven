package cn.net.arven.home.config;

import cn.net.arven.common.constant.Constant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class MyInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().startsWith("/manage/")) {
            System.err.println(request.getRequestURI());
            HttpSession session = request.getSession();
            if(Constant.USERNAME.equals(session.getAttribute("username"))&&
                    Constant.PASSWORD.equals(session.getAttribute("password")) ){
                return true;
            }
            response.sendRedirect("/login.html");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}