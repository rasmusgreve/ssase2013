/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.listener;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cly-vs
 */
public class FilterXHTML implements Filter {
    FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        if(uri.contains("f")) {
            chain.doFilter(request, response);
        } else {
            uri="f/login.xhtml";
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(uri);
            //chain.doFilter(request, response);
        }
        
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
    
}
