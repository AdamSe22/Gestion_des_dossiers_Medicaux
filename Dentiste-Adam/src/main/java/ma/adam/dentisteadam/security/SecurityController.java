package ma.adam.dentisteadam.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping(path = "/login")
    private String login()
    {
        return "login";
    }
}
