package com.viqsystems.chapternine.Configuration;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationLogginFilterBetter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestId = request.getHeader("Request-Id");
        logger.info("Successfully authenticated request with id " +
                requestId);
        filterChain.doFilter(request, response);
    }
}

/* Observations of OncePerRequestFilter
1- It supports only HTTP requests, but that’s actually what we always use. The advantage
is that it casts the types, and we directly receive the requests as HttpServletRequest and HttpServletResponse. Remember, with the Filter interface,
we had to cast the request and the response.
2-You can implement logic to decide if the filter is applied or not. Even if you added the
filter to the chain, you might decide it doesn’t apply for certain requests. You
set this by overriding the shouldNotFilter(HttpServletRequest) method. By default, the filter applies to all requests.
3- By default, a OncePerRequestFilter doesn’t apply to asynchronous requests or error dispatch requests.
You can change this behavior by overriding the methods shouldNotFilterAsyncDispatch() and shouldNotFilterErrorDispatch().
* */
