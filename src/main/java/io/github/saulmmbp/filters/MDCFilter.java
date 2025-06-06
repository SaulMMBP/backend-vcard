package io.github.saulmmbp.filters;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class MDCFilter implements Filter {

    private static final String CORRELATION_ID = "X-Correlation-Id";
    private boolean isCorrelationIdRegistered = false;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            isCorrelationIdRegistered = registerCorrelationId((HttpServletRequest) request);
            chain.doFilter(request, response);
        } finally {
            removeMDCEntries();
        }
    }
    
    private boolean registerCorrelationId(HttpServletRequest request) {
        String correlationId = request.getHeader(CORRELATION_ID);
        if (correlationId == null || correlationId.isBlank()) return false; 
        try {
            UUID.fromString(correlationId);
            MDC.put(CORRELATION_ID, correlationId);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    
    private void removeMDCEntries() {
        if (isCorrelationIdRegistered) MDC.remove(CORRELATION_ID);
    }
    
}
