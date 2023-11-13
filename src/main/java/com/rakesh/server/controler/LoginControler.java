package com.rakesh.server.controler;

import com.rakesh.server.global.GlobalData;
import com.rakesh.server.model.Role;
import com.rakesh.server.model.User;
import com.rakesh.server.repo.RoleRepo;
import com.rakesh.server.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginControler {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @GetMapping("/login")
    public String login()
    {
        GlobalData.cart.clear();
        return "login";
    }
    @GetMapping("/register")
    public String registerGet()
    {
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException
    {
        String  password =user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles=new ArrayList<>();
        roles.add(roleRepo.findById(2).get());
        user.setRoles(roles);
        userRepo.save(user);
        request.login(user.getEmail(),password);

        return "redirect:/";
    }

}
