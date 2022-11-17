package redis1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis1.Entitie.JPA.UserJpa;
import redis1.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<UserJpa> get(){
        return userService.readAll();
    }

    @GetMapping("/{id}")
    public UserJpa readOne(@PathVariable Long id){
        return userService.readOne(id);
    }


    @PostMapping("/")
    public void post(@RequestBody UserJpa user){
        userService.create(user);
    }

    @PutMapping("/{id}")
    public void update(long id, UserJpa user){
        userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(long id){
        userService.delete(id);
    }
}
