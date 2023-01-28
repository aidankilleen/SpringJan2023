package ie.pt.springwebsecurityinvestigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;

@Controller
public class MainController {


    @Autowired
    DataSource ds;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthorityRepo authRepo;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "Homepage");
        return "index";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {

        model.addAttribute("title", "Contact Us");
        return "contact";
    }

    @RequestMapping("/about")
    public String about(Model model) {

        model.addAttribute("title", "About Us");
        return "about";
    }
/*
    @RequestMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("title", "Logout");
        return "logout";
    }
	*/
}
