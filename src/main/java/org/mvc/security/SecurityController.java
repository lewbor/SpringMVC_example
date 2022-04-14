package org.mvc.security;

import org.mvc.lesson.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class SecurityController {

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpSession session) {
        session.setAttribute(
                "error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")
        );
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("person", new PersonDto());
        return "register";
    }

    @PostMapping(value = "/register")
    public String addUser(@ModelAttribute("person") @Valid PersonDto person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        Person entity = new Person();
        entity.setEmail(person.getEmail());
        entity.setPassword(passwordEncoder.encode(person.getPassword()));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        userDetailsService.createUser(entity);
        return "redirect:/login";
    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }
        return error;
    }
}
