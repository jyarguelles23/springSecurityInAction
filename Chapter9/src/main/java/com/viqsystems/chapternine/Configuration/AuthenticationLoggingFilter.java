package com.viqsystems.chapternine.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticationLoggingFilter implements Filter {

    private final Logger logger =
            Logger.getLogger(
                    AuthenticationLoggingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        var httpRequest = (HttpServletRequest) request;

        String requestId= httpRequest.getHeader("Request-Id");

        logger.info("Successfully authenticated request with id " + requestId);
        filterChain.doFilter(request, response);
    }
}
