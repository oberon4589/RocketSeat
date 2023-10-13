package br.com.skinkalabz.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.skinkalabz.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

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

        // Verificar se o usuário e senha estão corretos
        var user = this.userRepository.findByUsername(username);
        if (user == null) {
            response.sendError(401);
        } else {
            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (passwordVerify.verified) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(401);
            }
        }
        // Se estiverem corretos, deixa a requisição passar

        filterChain.doFilter(request, response);
    }

}
