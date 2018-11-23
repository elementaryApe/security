package com.herman.security.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author hsh
 * @create 2018-11-16 10:22
 **/
//@Component //模拟手动加载第三方filter使用@Configuration
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("  TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("  TimeFilter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("  TimeFilter end:"+ (new Date().getTime()-start));

    }

    @Override
    public void destroy() {
        System.out.println("  TimeFilter destroy");

    }
}
