package ho.buddy.controller;

import ho.buddy.config.auth.CustomUserContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

//    @GetMapping("/signin")
//    public String loginPage() {
//        return "user/login";
//    }

    @GetMapping("/auth/signin")
    public String login(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "exception", required = false) String exception,
        Model model
    ) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        System.out.println("error: " + error);
        System.out.println("exception: " + exception);

        return "/user/login";
    }

    @GetMapping("/auth/signup")
    public String register() {
        return "user/register";
    }

    @GetMapping("/user/info")
    public String userInfo(Model model, @AuthenticationPrincipal CustomUserContext principal) {
        model.addAttribute("member", principal.getMember());
        return "user/info";
    }

    @GetMapping("/user/info/edit")
    public String userInfoEdit(Model model, @AuthenticationPrincipal CustomUserContext principal) {

        model.addAttribute("member", principal.getMember());
        return "user/edit";
    }
}
