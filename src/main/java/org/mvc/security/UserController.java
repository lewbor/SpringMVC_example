package org.mvc.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    @RequestMapping("/user")
    public HashMap<String, Object> user(Principal user, HttpSession session) {
        List<String> flash = (List<String>) session.getAttribute("flash");
        if (flash == null) {
            flash = Arrays.asList("error", "info");
            session.setAttribute("flash", flash);
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("User", user);
        data.put("Session", session.getAttribute("flash"));

        //return user;
        return data;
    }
}
