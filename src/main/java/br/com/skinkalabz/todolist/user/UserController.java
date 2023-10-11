package br.com.skinkalabz.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Modificador
 * public: significa que a classe UserController pode ser acessada por qualquer
 * outra classe
 * private: significa que a classe UserController só pode ser acessada por ela
 * mesma
 * protected: significa que a classe UserController só pode ser acessada por ela
 * mesma e por classes que a estendem
 */

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * String (texto)
     * Integer (número inteiro)
     * Double (número decimal)
     * Boolean (verdadeiro ou falso)
     * Float (número decimal)
     * Char (caractere)
     * Date (data)
     * Void (vazio)
     */

    /**
     * Body precisa ser passado pois é o corpo da requisição
     */

    @PostMapping("/")
    public void create(@RequestBody UserModel userModel) {
        System.out.println(userModel.getUsername());
    }

}
