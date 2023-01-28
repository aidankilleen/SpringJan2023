package ie.pt.springbootwebinvestigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    UserDao dao;

    @RequestMapping("/")
    public String getHome(Model model){

        model.addAttribute("title", "Main Controller Home Page");
        User user = new User(1, "Alice", "alice@gmail.com", true);
        model.addAttribute("user", user);

        return "homepage";
    }

    @RequestMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", dao.getUsers());
        return "userlist";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(Model model, @RequestParam int id) throws UserDaoException {

        //dao.deleteUser(id);
        model.addAttribute("id", id);
        model.addAttribute("message",
                "Delete User " + id + "? Are you sure");
        return "deleteuser";
    }

    @PostMapping("/deleteuser")
    public ModelAndView deleteUser(ModelMap model,
                                   @RequestParam int id,
                                   @RequestParam String answer) throws UserDaoException {

        String message = "";
        if (answer.equals("OK")) {

            dao.deleteUser(id);

            message = "The user " + id + " was deleted";
            model.addAttribute("message", message);

            return new ModelAndView("userdeleted", model);

        } else {

            return new ModelAndView("redirect:/users", model);
        }
    }

    @GetMapping("/adduser")
    public String showAddUserForm (Model model) {

        model.addAttribute("title", "Add User");
        model.addAttribute("user", new User());
        return "userform";
    }

    @PostMapping("/adduser")
    public ModelAndView addUser(ModelMap model, @ModelAttribute User userToAdd) throws UserDaoException {

        System.out.println(userToAdd);
        dao.addUser(userToAdd);
        return new ModelAndView("redirect:/users", model);
    }

    @GetMapping("/updateuser")
    public String showUpdateUserForm(Model model, @RequestParam int id) throws UserDaoException {

        User userToEdit = dao.getUser(id);

        model.addAttribute("title", "Update User");
        model.addAttribute("user", userToEdit);

        return "userform";
    }

    @PostMapping("/updateuser")
    public ModelAndView updateUser(ModelMap model, @ModelAttribute User userToUpdate) throws UserDaoException {

        dao.updateUser(userToUpdate);
        return new ModelAndView("redirect:/users", model);

    }
    public String getAbout() {
        return "about";
    }
    @RequestMapping("/contact")
    public String getContact() {
        return "contact";
    }
}
