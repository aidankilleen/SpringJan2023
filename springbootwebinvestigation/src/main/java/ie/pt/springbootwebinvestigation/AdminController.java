package ie.pt.springbootwebinvestigation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("")
    public String getAdminHome() {
        return "admin";
    }

    @RequestMapping("/login")
    public String getAdminLogin() {

        return "loginxxxx";
    }
}
