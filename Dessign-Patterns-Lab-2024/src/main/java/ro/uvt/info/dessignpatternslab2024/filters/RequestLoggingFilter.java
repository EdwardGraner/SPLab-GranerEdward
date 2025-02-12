package ro.uvt.info.dessignpatternslab2024.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("Logging Request: " + httpRequest.getMethod() + " " + httpRequest.getRequestURI());
        chain.doFilter(request, response);
        System.out.println("Response sent for: " + httpRequest.getMethod() + " " + httpRequest.getRequestURI());
    }
}
