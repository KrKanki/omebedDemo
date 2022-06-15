package com.oembed.sanghyunKim.filter;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( "/*")
public class HttpEncryptionFilter implements Filter {

    @Value("${spring.http.encoding.charset}")
    String ENCODING;

    @Value("${spring.http.encoding.enabled}")
    boolean encodingEnabled;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if(encodingEnabled) {
            servletRequest.setCharacterEncoding(ENCODING);
            servletResponse.setCharacterEncoding(ENCODING);

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


}
