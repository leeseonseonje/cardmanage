package cardmanage.cardmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("")
    public String home() {
        return "home";
    }

    @RequestMapping("/{memberId}")
    public String loginHome(@PathVariable Long memberId) {
        return "loginHome";
    }
}
