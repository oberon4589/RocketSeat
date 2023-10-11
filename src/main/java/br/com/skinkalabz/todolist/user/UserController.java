package br.com.skinkalabz.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public UserModel create(@RequestBody UserModel userModel) {
        var userCreated = this.userRepository.save(userModel);
        return userCreated;
    }

}
