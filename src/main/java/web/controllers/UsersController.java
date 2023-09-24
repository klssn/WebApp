package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
//@RequestMapping("/users")
public class UsersController {

    private UserService service;

    @Autowired
    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", service.allUsers());
        return "main";
    }

    @PostMapping("/addUser")
    public String addUser(User user) {
        service.addUser(user.getUsername(), user.getEmail());
        return "redirect:/users";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam int id) {
        service.removeUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable int id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "edit";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("id") int id, User user) {
        service.editUser(user, user.getUsername(), user.getEmail(), id); //TODO
        return "redirect:/users";
    }
}