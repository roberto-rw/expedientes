package com.example.expedientes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JWTUtils jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        String name = null;
        String jwt = null;

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try{
                name = jwtUtil.extractParameterFromToken(jwt, "nombre");
            }catch (Exception e){
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token invalido");
                System.out.println("ERROR: Token invalido");
                return;
            }
        }

        System.out.println("Token: " + jwt);

        if (!jwtUtil.validateToken(jwt)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            System.out.println("ERROR: Token invalido");
            return;
        }

        String role = jwtUtil.extractParameterFromToken(jwt, "rol");

        if(!(role.equals("PACIENTE") || role.equals("MEDICO"))){
            System.out.println("ERROR: La peticion no es ni de un paciente ni de un medico");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            return;
        }

        if (role.equals("PACIENTE")) {
            System.out.println("INFO: La peticion es de un [PACIENTE]");
            if (request.getRequestURI().equals("/expedientes/uploadFile")) {
                chain.doFilter(request, response);
                return;
            }
            System.out.println("ERROR: al subir documento del paciente");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");

        } else if (role.equals("MEDICO")) {
            System.out.println("INFO: La peticion es de un [MEDICO]");
            if (request.getRequestURI().equals("/expedientes/downloadFile")) {
                chain.doFilter(request, response);
                return;
            }
            System.out.println("ERROR: al descargar un expediente por parte del medico");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
        }
    }
}
