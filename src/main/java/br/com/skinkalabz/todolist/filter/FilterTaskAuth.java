package br.com.skinkalabz.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Pegar a autenticação (usuário e senha)
        var authorization = request.getHeader("Authorization");

        var authEncoded = authorization.substring("Basic".length()).trim(); // Remove o "Basic" do início da string

        byte[] authDecode = Base64.getDecoder().decode(authEncoded); // Decodifica a string

        var authString = new String(authDecode); // Transforma o array de bytes em string

        String[] credentials = authString.split(":"); // Divide a string em duas partes, separadas pelo ":"
        String username = credentials[0];
        String password = credentials[1];
        System.out.println("Authorization");
        System.out.println(username);
        System.out.println(password);

        // Verificar se o usuário e senha estão corretos
        // Se estiverem corretos, deixa a requisição passar

        filterChain.doFilter(request, response);
    }

}
