package ie.pt.springrestwithjwtsecurity.controller;

import ie.pt.springrestwithjwtsecurity.model.LoginDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping("test")
    public String test() {

        return "is this working?";
    }

    @PostMapping("/signin")
    public String signin(@RequestBody LoginDetails loginDetails) {

        System.out.println(loginDetails);
        return "thiswillbeatoken";
    }
}
