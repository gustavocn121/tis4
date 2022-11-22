package br.com.pucminas.hospital.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    private static final String LOCAL = "https://plf-es-2022-2-ti4-0658100-hosp-sao-francisco-2-deman-9cfx8vdnh.vercel.app/login"; // URL
    private static final String REMOTO = "https://plf-es-2022-2-ti4-0658100-hosp-sao-francisco-2-deman-9cfx8vdnh.vercel.app/login"; // OTHER URL

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String origin = request.getHeader("Origin");

        if (LOCAL.equals(origin)) {
            response.setHeader("Access-Control-Allow-Origin", LOCAL);
        } else if (REMOTO.equals(origin)) {
            response.setHeader("Access-Control-Allow-Origin", REMOTO);
        }

        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, X-Auth-Token, Content-Type, Authorization, email, senha");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(req, res);
        }
    }
}
